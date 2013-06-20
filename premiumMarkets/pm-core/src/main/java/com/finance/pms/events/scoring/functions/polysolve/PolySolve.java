/***************************************************************************
 *   Copyright (C) 2012 by Paul Lutus                                      *
 *   lutusp@arachnoid.com                                                  *
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *   This program is distributed in the hope that it will be useful,       *
 *   but WITHOUT ANY WARRANTY; without even the implied warranty of        *
 *   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the         *
 *   GNU General Public License for more details.                          *
 *                                                                         *
 *   You should have received a copy of the GNU General Public License     *
 *   along with this program; if not, write to the                         *
 *   Free Software Foundation, Inc.,                                       *
 *   59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.             *
 ***************************************************************************/
package com.finance.pms.events.scoring.functions.polysolve;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/*
 * PolySolve.java
 *
 * Created on Oct 16, 2009, 10:35:14 AM
 */
/**
 *
 * @author lutusp
 */
public final class PolySolve extends javax.swing.JApplet {

    private boolean applet = true;
    String nameVersion = "PolySolve 3.7";
    private String copyright = "Copyright \251 2012, P. Lutus -- http://www.arachnoid.com";
    public Color gridColor = new Color(192, 240, 192);
    public Color zeroColor = new Color(192, 192, 192);
    public Color lineColor = new Color(0, 0, 255);
    public Color dataColor = new Color(255, 0, 0);
    public double dotScale = 4.0;
    public String pageData;
    public String errorMsg = "";
    public boolean data_valid = false;
    public int poly_order = 2; // default order
    public double xmin, xmax, ymin, ymax;
    public double dataXmin, dataXmax;
    public double dataYmin, dataYmax;
    public double eps;
    public int listingForm = 0;
    public boolean error;
    public boolean reverseMode = false;
    private String defaultData = "-1 -1\n" + "0 3\n" + "1 2.5\n" + "2 5\n" + "3 4\n" + "5 2\n" + "7 5\n" + "9 4\n";
    ArrayList<Pair> userDataList;
    ArrayList<Double> terms;
    double result_cc;
    double result_se;

    public static void main(final String[] args) throws Exception {

        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        final PolySolve theApplet = new PolySolve();
        theApplet.applet = false;
        JFrame theFrame;
        theFrame = new JFrame(theApplet.nameVersion);
        //theFrame.addWindowListener(new FrameListener());
        theFrame.setSize(700, 800);
        theFrame.add("Center", theApplet);
        theApplet.init();
        theApplet.start();
        System.setProperty("awt.useSystemAAFontSettings", "on");
        System.setProperty("swing.aatext", "true");
        theFrame.setVisible(true);
    }

    /** Initializes the applet PolySolve */
    @Override
    public void init() {
        try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {

                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        if (applet) {
            copyButton.setEnabled(false);
            tabbed_pane.remove(resultsPane);
            mainPanel.remove(topPanel);
            tableCopyButton.setEnabled(false);
            GridBagLayout layout = (GridBagLayout) mainPanel.getLayout();
            GridBagConstraints con = layout.getConstraints(bottomPanel);
            con.weighty = 0;
            layout.setConstraints(bottomPanel, con);
            con = layout.getConstraints(middlePanel);
            con.weighty = 3;
            layout.setConstraints(middlePanel, con);
            tableResultTextArea.setText("\n\n    See \"Results Area\" below.");

        }
        dataTextArea.getDocument().addDocumentListener(new MyDocumentListener());
        dataTextArea.setText(defaultData);
        process(true);
    }

    class MyDocumentListener implements DocumentListener {

        public void insertUpdate(DocumentEvent e) {
            process(true);
        }

        public void removeUpdate(DocumentEvent e) {
            process(true);
        }

        public void changedUpdate(DocumentEvent e) {
            process(true);
        }
    };

    void readData() {
        userDataList = new ArrayList<Pair>();
        double x = 0, y = 0, q = 0;
        String s;
        if (applet) {
//            JSObject window = JSObject.getWindow(this);
//            JSObject doc = (JSObject) window.getMember("document");
//            JSObject form = (JSObject) doc.getMember("form");
//            JSObject field = (JSObject) form.getMember("entries");
//            s = (String) field.getMember("value");
        } else {
            s = dataTextArea.getText();
        }
        try {
//            // filter all but numerical entry characters
//            // and trim beginning and end ws
//            s = s.replaceAll("[^\\.0-9eE+-]+", " ").trim();
//            // create array of numerical values
//            String[] num_array = s.split(" ");
//            boolean paired = true;
//            // parse array using new iterator syntax
//            for (String qs : num_array) {
//                if (!qs.equalsIgnoreCase("e")) {
//                    try {
//                        q = Double.valueOf(qs).doubleValue();
//                    } catch (Exception e) {
//                        String es = "";
//                        if (qs.length() == 0) {
//                            es = "No data.";
//                        } else {
//                            es = "Cannot parse \"" + qs + "\" in input.";
//                        }
//                        throw new Exception(es);
//                    }
//
//                    if (paired) {
//                        x = q;
//                    } else {
//                        y = q;
//                        if (reverseMode) {
//                            double t = x;
//                            x = y;
//                            y = t;
//                        }
//                        userDataList.add(new Pair(x, y));
//                        ymin = Math.min(y, ymin);
//                        ymax = Math.max(y, ymax);
//                        xmin = Math.min(x, xmin);
//                        xmax = Math.max(x, xmax);
//                    }
//                    paired = !paired;
//                }
//            }
//            if (!paired) {
//                throw new Exception("Data not in x,y pairs (odd number of entries) ... edit data.");
//            }
        } catch (Exception e) {
            data_valid = false;
            errorMsg = e.getMessage();
            userDataList.clear();
        }
    }

    void getData(boolean update) {
        eps = 1e-12;
        xmin = 1e30;
        ymin = xmin;
        xmax = 1e-30;
        ymax = xmax;
        readData();
        int n = userDataList.size();
        if (n > 0) {
            if (Math.abs(xmin - xmax) < 1e-3) {
                xmin -= 1e-3;
                xmax += 1e-3;
            }
            if (Math.abs(ymin - ymax) < 1e-3) {
                ymin -= 1e-3;
                ymax += 1e-3;
            }

            dataXmax = xmax;
            dataXmin = xmin;
            dataYmax = ymax;
            dataYmin = ymin;
            if (update) {
                tableStartTextField.setText(String.format("%.2f", dataXmin));
                tableEndTextField.setText(String.format("%.2f", dataXmax));
                double step = (dataXmax - dataXmin) / 20;
                tableStepSizeTextField.setText(String.format("%.2f", step));
            }
            double q = (ymax - ymin) / 6;
            ymin -= q;
            ymax += q;
            q = (xmax - xmin) / 6;
            xmin -= q;
            xmax += q;
        }
    }

    void p(String s) {
        System.out.println(s);
    }

    public void process(boolean update) {
        //pageData = dataTextArea.getText();
        data_valid = false;
        poly_order = (poly_order < 0) ? 0 : poly_order;
        poly_order = (poly_order > 512) ? 512 : poly_order;
        degreeTextField.setText("" + poly_order);
        errorMsg = "";
        getData(update);
        int size = userDataList.size();
        if (size > 1) {
            terms = MatrixFunctions.compute_coefficients(userDataList, poly_order);
            result_cc = MatrixFunctions.corr_coeff(userDataList, terms);
            result_se = MatrixFunctions.std_error(userDataList, terms);
            String r = showResult();
            writeResult(r, resultText);
            data_valid = true;
        } else {
            data_valid = false;
            if (errorMsg.length() == 0) {
                errorMsg = "Insufficient Data (minimum of 2 data pairs needed).";
            }
            writeResult("", resultText);
        }
//        GraphPanel gp = (GraphPanel) middlePanel;
//        gp.repaint();
    }

    private void writeAppletResult(String s) {
//        JSObject window = JSObject.getWindow(this);
//        JSObject doc = (JSObject) window.getMember("document");
//        JSObject form = (JSObject) doc.getMember("form");
//        JSObject field = (JSObject) form.getMember("results");
//        field.setMember("value", s);
    }

    private void writeResult(String s, JTextArea jta) {
        if (applet) {
            writeAppletResult(s);
        } else {
            jta.setText(s);
        }

    }

    void newDegree(int v) {
        poly_order += v;
        poly_order = (poly_order < 0) ? 0 : poly_order;
        process(false);
    }

    void setDegree(int v) {
        poly_order = v;
        newDegree(0);
    }

    void changeListingStyle() {
        listingForm++;
        listingForm %= 4;
        process(false);
    }

    String formatNum(double n, boolean wide) {
        String w = (wide) ? "21" : "";
        return String.format("%" + w + ".12e", n);
    }

    String showResult() {

        String styleTag[] = {
            "",
            "",
            "pow",
            "Math.pow"
        };
        String styleTitle[] = {
            "simple list",
            "mathematical function",
            "C/C++ function",
            "Java function"
        };

        int n = userDataList.size();
        String r = "Mode: " + ((reverseMode) ? "reverse (x,y = y,x)" : "normal") + "\n";
        r += "Polynomial degree " + poly_order + ", " + n + " x,y data pairs\n";
        r += "Correlation coefficient (r^2) = " + formatNum(result_cc, false) + "\n";
        r += "Standard error = " + formatNum(result_se, false) + "\n";
        r += "Output form: " + styleTitle[listingForm] + ":\n\n";
        r += (listingForm > 1) ? "double f(double x) {\n    return" : (listingForm > 0) ? "f(x) =" : "";
        for (int i = 0; i <= poly_order; i++) {
            double a = terms.get(i);
            if (i > 0) {
                if (listingForm > 1) {
                    r += "    ";
                }
                if (listingForm > 0) {
                    r += "     +";
                }
            }
            r += formatNum(a, true);
            if (i == 1 && listingForm > 0) {
                r += " * x";
            }
            if (i > 1) {
                if (listingForm > 1) {
                    r += (" * " + styleTag[listingForm] + "(x," + i + ")");
                } else if (listingForm == 1) {
                    r += (" * x^" + i);
                }
            }
            if (i < poly_order) {
                r += "\n";
            }
        }
        if (listingForm > 1) {
            r += ";\n}";
        }
        if (poly_order > n - 1) {
            r += "\n\nWarning: Polynomial degree exceeds data size - 1.";
        }
        r += "\n\n" + copyright + ". All Rights Reserved.";
        return r;
    }

    void show_mat(double[][] data) {
        for (double[] y : data) {
            for (double x : y) {
                System.out.printf("%25.16g,", x);
            }
            System.out.println("");
        }
        System.out.println("***************");
    }

    // as simple as I could make it
    // given the misbehavior of polynomials
    double findRoot2(double y, double x, ArrayList<Double> terms, double scale) {
        int max = 256;
        boolean positive = true;
        double epsilon = 1e-8;
        double dy = 0;
        double ody = Double.NaN;
        while (Math.abs(dy = (MatrixFunctions.regress(x, terms) - y)) > epsilon && max-- > 0) {
            if (Double.isInfinite(x)) {
                break;
            }
            if (!Double.isNaN(ody)) {
                if (Math.abs(dy) > ody) {
                    positive = !positive;
                }
            }
            ody = Math.abs(dy);
            dy *= scale;
            x += (positive) ? dy : -dy;
        }
        if (max <= 0 || Double.isInfinite(x)) {
            x = Double.NaN;
        }

        return x;
    }

    // begin with small steps, if algorithm fails
    // gradually make them larger
    double findRoot(double y, double x, ArrayList<Double> terms) {
        double scale = Math.pow(2, -32);
        int max = 64;
        double rx;
        while (Double.isNaN(rx = findRoot2(y, x, terms, scale)) && max-- > 0) {
            scale *= 2.0;
        }
        return rx;
    }

    double plotFunct(double x) {
        return MatrixFunctions.regress(x, terms);
    }

    void clipCopyText(String s) {
//        ClipboardFunctions.clipCopy(s);
    }

    void clipCopyResult() {
        clipCopyText(resultText.getText());
    }

    void clipCopyTable() {
        clipCopyText(this.tableResultTextArea.getText());
    }

    void clipPaste() {
//        String data = ClipboardFunctions.clipPaste(this);
//        data = dataTextArea.getText() + data;
//        dataTextArea.setText(data);
    }

    void xForY(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String ys = yEntryTextfield.getText();
            String xs = yResultTextfield.getText();
            double y = Double.parseDouble(ys);
            double x = Double.parseDouble(xs);
            if (Double.isNaN(x) || Double.isInfinite(x)) {
                x = 0;
            }
            double rx = findRoot(y, x, terms);
            xs = formatNum(rx, false);
            yResultTextfield.setText(xs);
        }
    }

    void yForX(KeyEvent evt) {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String xs = xEntryTextfield.getText();
            double x = Double.parseDouble(xs);
            double y = MatrixFunctions.regress(x, terms);
            String ys = formatNum(y, false);
            xResultTextfield.setText(ys);
        }
    }

    void trackMouse(MouseEvent evt) {
//        int x = evt.getX();
//        int y = evt.getY();
//        Pair mp = ((GraphPanel) middlePanel).mousePos(x, y);
//        String sx = String.format("%.4g", mp.x);
//        String sy = String.format("%.4g", mp.y);
//        posLabel.setText("x = " + sx + ", y = " + sy);
    }
    
    double ntrp(double xa,double xb,double ya,double yb,double x) {
        return (x-xa) * (yb-ya) / (xb-xa) + ya;
    }

    String generateRow(double a, double p, double b, int places) {
        double pct = ntrp(a,b,0,100,p);
        double y = MatrixFunctions.regress(p, terms);
        String format = String.format("%%.%df,%%.%df,%%.%df\n", places, places, places);
        String row = String.format(format, p, y, pct);
        return row;
    }

    public void generateTable() {
        try {
            double a = Double.parseDouble(tableStartTextField.getText());
            double b = Double.parseDouble(tableEndTextField.getText());
            double stepSize = Double.parseDouble(tableStepSizeTextField.getText());
            int places = Integer.parseInt(decimalPlacesTextField.getText());
            if (stepSize <= 0) {
                throw new Exception("Step size must be > 0");
            }
            double p = a;
            double lp = 0;
            StringBuilder sb = new StringBuilder();
            String oldRow = "";
            sb.append("x,y,%\n");
            while (p <= b) {
                lp = p;
                oldRow = generateRow(a, p, b, places);
                sb.append(oldRow);
                p += stepSize;
            }
            // generate last row if needed
            if (lp < b) {
                String newRow = generateRow(a, b, b, places);
                if (!(oldRow.equals(newRow))) {
                    sb.append(newRow);
                }
            }
            writeResult(sb.toString(), tableResultTextArea);
        } catch (Exception e) {
            writeResult(e.toString(), tableResultTextArea);
        }
    }

    void setReverseMode() {
        reverseMode = this.reverseCheckBox.isSelected();
        process(true);
    }

    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        bottomPanel = new javax.swing.JPanel();
        copyButton = new javax.swing.JButton();
        cfuncButton = new javax.swing.JButton();
        solveButton = new javax.swing.JButton();
        rightButton = new javax.swing.JButton();
        degreeTextField = new javax.swing.JTextField();
        leftButton = new javax.swing.JButton();
        degreeLabel = new javax.swing.JLabel();
        reverseCheckBox = new javax.swing.JCheckBox();
        posLabel = new javax.swing.JLabel();
        tabbed_pane = new javax.swing.JTabbedPane();
        resultsPane = new javax.swing.JPanel();
        bottom_panel_scroll_pane = new javax.swing.JScrollPane();
        resultText = new javax.swing.JTextArea();
        Tables = new javax.swing.JPanel();
        tableTopPanel = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        tableStartTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tableEndTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tableStepSizeTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        decimalPlacesTextField = new javax.swing.JTextField();
        tableGenButton = new javax.swing.JButton();
        tableCopyButton = new javax.swing.JButton();
        tableMidPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableResultTextArea = new javax.swing.JTextArea();
        computePane = new javax.swing.JPanel();
        xlabel = new javax.swing.JLabel();
        xEntryTextfield = new javax.swing.JTextField();
        xResultTextfield = new javax.swing.JTextField();
        ylabel = new javax.swing.JLabel();
        entryLabel = new javax.swing.JLabel();
        resultLabel = new javax.swing.JLabel();
        xlabel1 = new javax.swing.JLabel();
        yEntryTextfield = new javax.swing.JTextField();
        ylabel1 = new javax.swing.JLabel();
        yResultTextfield = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
//        middlePanel = new GraphPanel(this);
        topPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        pasteButton = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridBagLayout());

        mainPanel.setLayout(new java.awt.GridBagLayout());

        bottomPanel.setLayout(new java.awt.GridBagLayout());

        copyButton.setText("Copy Result");
        copyButton.setToolTipText("Copy the result summary to the system clipboard");
        copyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                copyButtonMouseClicked(evt);
            }
        });
        copyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                copyButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(copyButton, gridBagConstraints);

        cfuncButton.setText("Form");
        cfuncButton.setToolTipText("Cycle through simple result, mathematical, C/C++ and Java function result forms");
        cfuncButton.setActionCommand("Listing Style");
        cfuncButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cfuncButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(cfuncButton, gridBagConstraints);

        solveButton.setText("Solve");
        solveButton.setToolTipText("Force recalculation");
        solveButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solveButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(solveButton, gridBagConstraints);

        rightButton.setText("->");
        rightButton.setToolTipText("Increase polynomial degree");
        rightButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rightButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(rightButton, gridBagConstraints);

        degreeTextField.setEditable(false);
        degreeTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        degreeTextField.setText("1");
        degreeTextField.setToolTipText("Current polynomial fit degree");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(degreeTextField, gridBagConstraints);

        leftButton.setText("<-");
        leftButton.setToolTipText("Decrease polynomial degree");
        leftButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leftButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(leftButton, gridBagConstraints);

        degreeLabel.setHorizontalAlignment(javax.swing.SwingConstants.TRAILING);
        degreeLabel.setText("Degree:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 4, 2, 4);
        bottomPanel.add(degreeLabel, gridBagConstraints);

        reverseCheckBox.setText("Reverse");
        reverseCheckBox.setToolTipText("Switch data order (x,y = y,x)");
        reverseCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reverseCheckBoxMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 4;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(reverseCheckBox, gridBagConstraints);

        posLabel.setText("x = 0.0, y = 0.0");
        posLabel.setMinimumSize(new java.awt.Dimension(120, 15));
        posLabel.setPreferredSize(new java.awt.Dimension(120, 15));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        bottomPanel.add(posLabel, gridBagConstraints);

        tabbed_pane.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);

        resultsPane.setLayout(new java.awt.GridBagLayout());

        resultText.setColumns(20);
        resultText.setFont(new java.awt.Font("Courier New", 0, 12));
        resultText.setRows(5);
        resultText.setToolTipText("View or copy computation results here \n");
        resultText.setMargin(new java.awt.Insets(4, 4, 4, 4));
        bottom_panel_scroll_pane.setViewportView(resultText);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        resultsPane.add(bottom_panel_scroll_pane, gridBagConstraints);

        tabbed_pane.addTab("Results", resultsPane);

        Tables.setLayout(new java.awt.BorderLayout());

        tableTopPanel.setLayout(new java.awt.GridBagLayout());

        jLabel3.setText("Start:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tableTopPanel.add(jLabel3, gridBagConstraints);

        tableStartTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tableStartTextField.setText("0.00");
        tableStartTextField.setToolTipText("Table x-coordinate start value");
        tableStartTextField.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        tableTopPanel.add(tableStartTextField, gridBagConstraints);

        jLabel4.setText("End:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tableTopPanel.add(jLabel4, gridBagConstraints);

        tableEndTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tableEndTextField.setText("0.00");
        tableEndTextField.setToolTipText("Table x-coordinate end value");
        tableEndTextField.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        tableTopPanel.add(tableEndTextField, gridBagConstraints);

        jLabel5.setText("Step:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tableTopPanel.add(jLabel5, gridBagConstraints);

        tableStepSizeTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        tableStepSizeTextField.setText("10");
        tableStepSizeTextField.setToolTipText("Size of x-coordinate table step (distance between rows)");
        tableStepSizeTextField.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        tableTopPanel.add(tableStepSizeTextField, gridBagConstraints);

        jLabel6.setText("Decimals:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tableTopPanel.add(jLabel6, gridBagConstraints);

        decimalPlacesTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        decimalPlacesTextField.setText("2");
        decimalPlacesTextField.setToolTipText("Decimal precision in result data (integer)");
        decimalPlacesTextField.setName(""); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        tableTopPanel.add(decimalPlacesTextField, gridBagConstraints);

        tableGenButton.setText("Generate Table");
        tableGenButton.setToolTipText("Create a table using entered values");
        tableGenButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableGenButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tableTopPanel.add(tableGenButton, gridBagConstraints);

        tableCopyButton.setText("Copy Table");
        tableCopyButton.setToolTipText("Copy a generated table to the system clipboard");
        tableCopyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableCopyButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 2);
        tableTopPanel.add(tableCopyButton, gridBagConstraints);

        Tables.add(tableTopPanel, java.awt.BorderLayout.NORTH);

        tableMidPanel.setLayout(new java.awt.BorderLayout());

        tableResultTextArea.setColumns(20);
        tableResultTextArea.setRows(5);
        tableResultTextArea.setToolTipText("View or copy generated table here");
        jScrollPane2.setViewportView(tableResultTextArea);

        tableMidPanel.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        Tables.add(tableMidPanel, java.awt.BorderLayout.CENTER);

        tabbed_pane.addTab("Table Generator", Tables);

        computePane.setLayout(new java.awt.GridBagLayout());

        xlabel.setText("y = f(x): x");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 5, 4);
        computePane.add(xlabel, gridBagConstraints);

        xEntryTextfield.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        xEntryTextfield.setText("0.0");
        xEntryTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                xEntryTextfieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        computePane.add(xEntryTextfield, gridBagConstraints);

        xResultTextfield.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        xResultTextfield.setText("0.0");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 2, 0);
        computePane.add(xResultTextfield, gridBagConstraints);

        ylabel.setText("y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 5, 4);
        computePane.add(ylabel, gridBagConstraints);

        entryLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        entryLabel.setText("Argument");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        computePane.add(entryLabel, gridBagConstraints);

        resultLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        resultLabel.setText("Result");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        computePane.add(resultLabel, gridBagConstraints);

        xlabel1.setText("x = f(y): y");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 7, 4);
        computePane.add(xlabel1, gridBagConstraints);

        yEntryTextfield.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        yEntryTextfield.setText("0.0");
        yEntryTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yEntryTextfieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        computePane.add(yEntryTextfield, gridBagConstraints);

        ylabel1.setText("x");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 4, 7, 4);
        computePane.add(ylabel1, gridBagConstraints);

        yResultTextfield.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        yResultTextfield.setText("0.0");
        yResultTextfield.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                yResultTextfieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 4, 0);
        computePane.add(yResultTextfield, gridBagConstraints);

        jScrollPane3.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setFont(new java.awt.Font("Courier New", 0, 12));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText("First row: type an X argument and press Enter for a Y result (y = f(x)).\nSecond row: type a Y argument and press Enter for an X result (x = f(y)).\nBecause x= f(y) may have multiple solutions, it may be necessary to enter an X estimate in the \"Result\" window.\nBeyond degree 2, it's not easy to obtain results for the x = f(y) case, regardless of the method used.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jScrollPane3.setViewportView(jTextArea1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        computePane.add(jScrollPane3, gridBagConstraints);

        tabbed_pane.addTab("Compute", computePane);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        bottomPanel.add(tabbed_pane, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        mainPanel.add(bottomPanel, gridBagConstraints);

        middlePanel.setBackground(new java.awt.Color(255, 255, 255));
        middlePanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        middlePanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                middlePanelMouseMoved(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        mainPanel.add(middlePanel, gridBagConstraints);

        topPanel.setLayout(new java.awt.GridBagLayout());

        dataTextArea.setColumns(20);
        dataTextArea.setFont(new java.awt.Font("Courier New", 0, 12));
        dataTextArea.setRows(5);
        dataTextArea.setToolTipText("Enter or paste (Ctrl+V) x,y data pairs here");
        dataTextArea.setMargin(new java.awt.Insets(4, 4, 4, 4));
        jScrollPane1.setViewportView(dataTextArea);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 2, 0);
        topPanel.add(jScrollPane1, gridBagConstraints);

        jLabel1.setText("Data x,y Pairs (any format)");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        topPanel.add(jLabel1, gridBagConstraints);

        pasteButton.setText("Paste");
        pasteButton.setToolTipText("Paste from clipboard to input");
        pasteButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pasteButtonMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        topPanel.add(pasteButton, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.3;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        mainPanel.add(topPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        getContentPane().add(mainPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void solveButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solveButtonMouseClicked
        // TODO add your handling code here:
        process(false);
    }//GEN-LAST:event_solveButtonMouseClicked

    private void rightButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rightButtonMouseClicked
        // TODO add your handling code here:
        newDegree(1);
    }//GEN-LAST:event_rightButtonMouseClicked

    private void leftButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftButtonMouseClicked
        // TODO add your handling code here:
        newDegree(-1);
    }//GEN-LAST:event_leftButtonMouseClicked

    private void cfuncButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cfuncButtonMouseClicked
        // TODO add your handling code here:
        changeListingStyle();
    }//GEN-LAST:event_cfuncButtonMouseClicked

    private void pasteButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pasteButtonMouseClicked
        // TODO add your handling code here:
        clipPaste();
    }//GEN-LAST:event_pasteButtonMouseClicked

    private void copyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_copyButtonMouseClicked
        // TODO add your handling code here:
        clipCopyResult();
    }//GEN-LAST:event_copyButtonMouseClicked

    private void xEntryTextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_xEntryTextfieldKeyReleased
        // TODO add your handling code here:
        yForX(evt);
    }//GEN-LAST:event_xEntryTextfieldKeyReleased

    private void yEntryTextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yEntryTextfieldKeyReleased
        // TODO add your handling code here:
        xForY(evt);
    }//GEN-LAST:event_yEntryTextfieldKeyReleased

    private void yResultTextfieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_yResultTextfieldKeyReleased
        // TODO add your handling code here:
        xForY(evt);
    }//GEN-LAST:event_yResultTextfieldKeyReleased

    private void middlePanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_middlePanelMouseMoved
        // TODO add your handling code here:
        trackMouse(evt);
    }//GEN-LAST:event_middlePanelMouseMoved

    private void tableGenButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableGenButtonMouseClicked
        // TODO add your handling code here:
        generateTable();
    }//GEN-LAST:event_tableGenButtonMouseClicked

    private void copyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_copyButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_copyButtonActionPerformed

    private void tableCopyButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableCopyButtonMouseClicked
        // TODO add your handling code here:
        clipCopyTable();
    }//GEN-LAST:event_tableCopyButtonMouseClicked

    private void reverseCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reverseCheckBoxMouseClicked
        // TODO add your handling code here:
        setReverseMode();
    }//GEN-LAST:event_reverseCheckBoxMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Tables;
    private javax.swing.JPanel bottomPanel;
    private javax.swing.JScrollPane bottom_panel_scroll_pane;
    private javax.swing.JButton cfuncButton;
    private javax.swing.JPanel computePane;
    private javax.swing.JButton copyButton;
    public javax.swing.JTextArea dataTextArea;
    private javax.swing.JTextField decimalPlacesTextField;
    private javax.swing.JLabel degreeLabel;
    private javax.swing.JTextField degreeTextField;
    private javax.swing.JLabel entryLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JButton leftButton;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel middlePanel;
    private javax.swing.JButton pasteButton;
    private javax.swing.JLabel posLabel;
    private javax.swing.JLabel resultLabel;
    private javax.swing.JTextArea resultText;
    private javax.swing.JPanel resultsPane;
    private javax.swing.JCheckBox reverseCheckBox;
    private javax.swing.JButton rightButton;
    private javax.swing.JButton solveButton;
    private javax.swing.JTabbedPane tabbed_pane;
    private javax.swing.JButton tableCopyButton;
    private javax.swing.JTextField tableEndTextField;
    private javax.swing.JButton tableGenButton;
    private javax.swing.JPanel tableMidPanel;
    private javax.swing.JTextArea tableResultTextArea;
    private javax.swing.JTextField tableStartTextField;
    private javax.swing.JTextField tableStepSizeTextField;
    private javax.swing.JPanel tableTopPanel;
    private javax.swing.JPanel topPanel;
    private javax.swing.JTextField xEntryTextfield;
    private javax.swing.JTextField xResultTextfield;
    private javax.swing.JLabel xlabel;
    private javax.swing.JLabel xlabel1;
    private javax.swing.JTextField yEntryTextfield;
    private javax.swing.JTextField yResultTextfield;
    private javax.swing.JLabel ylabel;
    private javax.swing.JLabel ylabel1;
    // End of variables declaration//GEN-END:variables
}
