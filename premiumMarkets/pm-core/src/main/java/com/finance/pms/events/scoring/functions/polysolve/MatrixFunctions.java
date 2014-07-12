/**
 * Premium Markets is an automated stock market analysis system.
 * It implements a graphical environment for monitoring stock markets technical analysis
 * major indicators, for portfolio management and historical data charting.
 * In its advanced packaging -not provided under this license- it also includes :
 * Screening of financial web sites to pick up the best market shares, 
 * Price trend prediction based on stock markets technical analysis and indices rotation,
 * Back testing, Automated buy sell email notifications on trend signals calculated over
 * markets and user defined portfolios. 
 * With in mind beating the buy and hold strategy.
 * Type 'Premium Markets FORECAST' in your favourite search engine for a free workable demo.
 * 
 * Copyright (C) 2008-2014 Guillaume Thoreton
 * 
 * This file is part of Premium Markets.
 * 
 * Premium Markets is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by 
 * the Free Software Foundation, either version 3 of the License, or 
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.finance.pms.events.scoring.functions.polysolve;

import java.util.ArrayList;


/**
 *
 * @author lutusp
 */
public final class MatrixFunctions {

    // don't allow this class to be instantiated
    private MatrixFunctions() {
    }

    // classic Gauss-Jordan matrix manipulation functions
    static private void gj_divide(double[][] A, int i, int j, int m) {
        for (int q = j + 1; q < m; q++) {
            A[i][q] /= A[i][j];
        }
        A[i][j] = 1;
    }

    static private void gj_eliminate(double[][] A, int i, int j, int n, int m) {
        for (int k = 0; k < n; k++) {
            if (k != i && A[k][j] != 0) {
                for (int q = j + 1; q < m; q++) {
                    A[k][q] -= A[k][j] * A[i][q];
                }
                A[k][j] = 0;
            }
        }
    }

    static void gj_echelonize(double[][] A) {
        int n = A.length;
        int m = A[0].length;
        int i = 0;
        int j = 0;
        int k;
        double[] swap;
        while (i < n && j < m) {
            //look for non-zero entries in col j at or below row i
            k = i;
            while (k < n && A[k][j] == 0) {
                k++;
            }
            // if an entry is found at row k
            if (k < n) {
                //  if k is not i, then swap row i with row k
                if (k != i) {
                    swap = A[i];
                    A[i] = A[k];
                    A[k] = swap;
                }
                // if A[i][j] is != 1, divide row i by A[i][j]
                if (A[i][j] != 1) {
                    gj_divide(A, i, j, m);
                }
                // eliminate all other non-zero entries
                gj_eliminate(A, i, j, n, m);
                i++;
            }
            j++;
        }
    }

    // produce a single y result for a given x
    static double regress(double x, ArrayList<Double> terms) {
        double a = 0;
        int exp = 0;
        for (double term : terms) {
            a += term * Math.pow(x, exp);
            exp++;
        }
        return a;
    }

    // correlation coefficient
    static double corr_coeff(ArrayList<Pair> data, ArrayList<Double> terms) {
        double r = 0;
        int n = data.size();
        double sx = 0, sx2 = 0, sy = 0, sy2 = 0, sxy = 0;
        double x, y;
        for (Pair pr : data) {
            x = regress(pr.x, terms);
            y = pr.y;
            sx += x;
            sy += y;
            sxy += x * y;
            sx2 += x * x;
            sy2 += y * y;
        }
        double div = Math.sqrt((sx2 - (sx * sx) / n) * (sy2 - (sy * sy) / n));
        if (div != 0) {
            r = Math.pow((sxy - (sx * sy) / n) / div, 2);
        }
        return r;
    }

    // standard error
    static double std_error(ArrayList<Pair> data, ArrayList<Double> terms) {
        double r = 0;
        int n = data.size();
        if (n > 2) {
            double a = 0;
            for (Pair pr : data) {
                a += Math.pow((regress(pr.x, terms) - pr.y), 2);
            }
            r = Math.sqrt(a / (n - 2));
        }
        return r;
    }

    // create regression coefficients
    // for provided data set
    static ArrayList<Double> compute_coefficients(ArrayList<Pair> data, int p) {
        p += 1;
        int n = data.size();
        int r, c;
        int rs = 2 * p - 1;
        //
        // by request: read each datum only once
        // not the most efficient processing method
        // but required if the data set is huge
        //
        // create square matrix with added RH column
        double[][] m = new double[p][p + 1];
        // create array of precalculated matrix data
        double[] mpc = new double[rs];
        mpc[0] = n;
        for (Pair pr : data) {
            // process precalculation array
            for (r = 1; r < rs; r++) {
                mpc[r] += Math.pow(pr.x, r);
            }
            // process RH column cells
            m[0][p] += pr.y;
            for (r = 1; r < p; r++) {
                m[r][p] += Math.pow(pr.x, r) * pr.y;
            }
        }
        // populate square matrix section
        for (r = 0; r < p; r++) {
            for (c = 0; c < p; c++) {
                m[r][c] = mpc[r + c];
            }
        }
        // reduce matrix
        gj_echelonize(m);
        // extract result column
        ArrayList<Double> terms = new ArrayList<Double>();
      
        for (double[] mc : m) {
            terms.add(mc[p]);
        }
        return terms;
    }
}
