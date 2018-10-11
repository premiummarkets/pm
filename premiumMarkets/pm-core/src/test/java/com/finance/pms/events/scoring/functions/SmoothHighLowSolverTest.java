package com.finance.pms.events.scoring.functions;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

public class SmoothHighLowSolverTest {

    SmoothHighLowSolver smoothHighLowSolver;

    @Before
    public void setUp() throws Exception {
        smoothHighLowSolver = new SmoothHighLowSolver();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void higherHigh() {
        SortedMap<Integer, Double> data;
        int smoothingPeriod = 0;
        int minimumNbDaysBetweenExtremes = 5;
        SortedMap<Integer, Double> _higherHighs = new TreeMap<>();
        Line<Integer, Double> _expertTangent = new Line<>();
        Double lowestStart = Double.NaN;
        Double highestStart = Double.NaN;
        Double lowestEnd = Double.NaN;
        Double highestEnd = Double.NaN;
        Double minSlope = Double.NaN;
        Double maxSlope = Double.NaN;
        Boolean hasHH = smoothHighLowSolver.higherHigh(
                generateData(),
                smoothingPeriod, minimumNbDaysBetweenExtremes,
                _higherHighs, _expertTangent,
                lowestStart, highestStart,
                lowestEnd, highestEnd,
                minSlope, maxSlope);
        assertTrue(hasHH);
    }

    @Test
    public void lowerLow() {
    }

    @Test
    public void higherLow() {
    }

    @Test
    public void lowerHigh() {
    }

    @Test
    public void flatHigh() {
    }

    @Test
    public void flatLow() {
    }

    private SortedMap<Integer, Double> generateData() {
        SortedMap<Integer, Double> result = new TreeMap<>();
        result.putAll(draw(0, 5, 1, +1)); //up
        result.putAll(draw(5, 10, 1, -1)); // = down
        return result;
    }

    private SortedMap<Integer, Double> draw(int from, int to, double extremeStart, double sign) {
        TreeMap<Integer, Double> result = IntStream.range(from, to).boxed().collect(
                Collectors.toMap(i -> i, i -> extremeStart + sign*i, (a, b) -> a, TreeMap::new));
        return result;
    }
}