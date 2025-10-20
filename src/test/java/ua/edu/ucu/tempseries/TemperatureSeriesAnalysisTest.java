package ua.edu.ucu.tempseries;

import static org.junit.Assert.*;
import java.util.InputMismatchException;
import org.junit.Test;
import ua.edu.ucu.apps.tempseries.TempSummaryStatistics;
import ua.edu.ucu.apps.tempseries.TemperatureSeriesAnalysis;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void test() {
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

   @Test
   public void testAverageWithOneElementArray() {
       // setup input data and expected result
       double[] temperatureSeries = {-1.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = -1.0;

       // call tested method
       double actualResult = seriesAnalysis.average();

       // compare expected result with actual result
       assertEquals(expResult, actualResult, 0.00001);
   }

   @Test(expected = IllegalArgumentException.class)
   public void testAverageWithEmptyArray() {
       double[] temperatureSeries = {};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

       // expect exception here
       seriesAnalysis.average();
   }

   @Test
   public void testAverage() {
       double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
       TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
       double expResult = 1.0;

       double actualResult = seriesAnalysis.average();

       assertEquals(expResult, actualResult, 0.00001);
   }
    
    @Test
    public void testDefaultConstructor() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        assertNotNull(seriesAnalysis);
    }

    @Test
    public void testConstructorWithValidData() {
        double[] temperatureSeries = {-1.0, 0.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructorWithInvalidTemperature() {
        double[] temperatureSeries = {20.0, -300.0, 30.0};
        new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test(expected = InputMismatchException.class)
    public void testConstructorWithMinimumInvalidTemperature() {
        double[] temperatureSeries = {-273.1};
        new TemperatureSeriesAnalysis(temperatureSeries);
    }

    @Test
    public void testConstructorWithAbsoluteZero() {
        double[] temperatureSeries = {-273.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(-273.0, seriesAnalysis.average(), 0.00001);
    }

    @Test
    public void testAverageWithNegativeNumbers() {
        double[] temperatureSeries = {-10.0, -20.0, -30.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -20.0;
        double actualResult = seriesAnalysis.average();
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testDeviation() {
        double[] temperatureSeries = {1.0, 2.0, 3.0, 4.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = Math.sqrt(2.0);
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.deviation();
    }

    @Test
    public void testDeviationWithOneElement() {
        double[] temperatureSeries = {5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.0;
        double actualResult = seriesAnalysis.deviation();
        assertEquals(expResult, actualResult, 0.00001);
    }


    @Test
    public void testMin() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -5.0;
        double actualResult = seriesAnalysis.min();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.min();
    }

    @Test
    public void testMinWithOneElement() {
        double[] temperatureSeries = {42.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(42.0, seriesAnalysis.min(), 0.00001);
    }

    @Test
    public void testMax() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 5.0;
        double actualResult = seriesAnalysis.max();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.max();
    }

    @Test
    public void testMaxWithOneElement() {
        double[] temperatureSeries = {-15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(-15.0, seriesAnalysis.max(), 0.00001);
    }

    @Test
    public void testFindTempClosestToZero() {
        double[] temperatureSeries = {-1.0, 0.5, 2.0, -0.3};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -0.3;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToZeroWithEqualDistance() {
        double[] temperatureSeries = {-0.5, 0.5, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 0.5;
        double actualResult = seriesAnalysis.findTempClosestToZero();
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToZeroWithZero() {
        double[] temperatureSeries = {-5.0, 0.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        assertEquals(0.0, seriesAnalysis.findTempClosestToZero(), 0.00001);
    }

    @Test
    public void testFindTempClosestToValue() {
        double[] temperatureSeries = {1.0, 5.0, 10.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 10.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(9.0);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test
    public void testFindTempClosestToValueWithEqualDistance() {
        double[] temperatureSeries = {5.0, 15.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 15.0;
        double actualResult = seriesAnalysis.findTempClosestToValue(10.0);
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.findTempClosestToValue(5.0);
    }

    @Test
    public void testFindTempsLessThan() {
        double[] temperatureSeries = {-1.0, 0.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsLessThan(1.5);
        assertArrayEquals(new double[]{-1.0, 0.0, 1.0}, result, 0.00001);
    }

    @Test
    public void testFindTempsLessThanEmpty() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsLessThan(0.0);
        assertEquals(0, result.length);
    }

    @Test
    public void testFindTempsLessThanAll() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsLessThan(10.0);
        assertEquals(3, result.length);
    }

    @Test
    public void testFindTempsGreaterThan() {
        double[] temperatureSeries = {-1.0, 0.0, 1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsGreaterThan(1.0);
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, result, 0.00001);
    }

    @Test
    public void testFindTempsGreaterThanEmpty() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsGreaterThan(10.0);
        assertEquals(0, result.length);
    }

    @Test
    public void testFindTempsGreaterThanAll() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsGreaterThan(-10.0);
        assertEquals(3, result.length);
    }

    @Test
    public void testFindTempsInRange() {
        double[] temperatureSeries = {-5.0, -1.0, 0.0, 1.0, 2.0, 3.0, 10.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsInRange(0.0, 3.0);
        assertArrayEquals(new double[]{0.0, 1.0, 2.0, 3.0}, result, 0.00001);
    }

    @Test
    public void testFindTempsInRangeEmpty() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsInRange(10.0, 20.0);
        assertEquals(0, result.length);
    }

    @Test
    public void testFindTempsInRangeAll() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.findTempsInRange(-10.0, 10.0);
        assertEquals(3, result.length);
    }

    @Test
    public void testReset() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.reset();
        try {
            seriesAnalysis.average();
            fail("Expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            // Expected
        }
    }

    @Test
    public void testSortTemps() {
        double[] temperatureSeries = {3.0, -1.0, 5.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.sortTemps();
        assertArrayEquals(new double[]{-1.0, 2.0, 3.0, 5.0}, result, 0.00001);
    }

    @Test
    public void testSortTempsAlreadySorted() {
        double[] temperatureSeries = {1.0, 2.0, 3.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.sortTemps();
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, result, 0.00001);
    }

    @Test
    public void testSortTempsReverseSorted() {
        double[] temperatureSeries = {3.0, 2.0, 1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double[] result = seriesAnalysis.sortTemps();
        assertArrayEquals(new double[]{1.0, 2.0, 3.0}, result, 0.00001);
    }

    @Test
    public void testSummaryStatistics() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        TempSummaryStatistics stats = seriesAnalysis.summaryStatistics();
        
        assertNotNull(stats);
        assertEquals(1.0, stats.getAvgTemp(), 0.00001);
        assertEquals(-5.0, stats.getMinTemp(), 0.00001);
        assertEquals(5.0, stats.getMaxTemp(), 0.00001);
        assertTrue(stats.getDevTemp() > 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSummaryStatisticsWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.summaryStatistics();
    }

    @Test
    public void testAddTemps() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int result = seriesAnalysis.addTemps(3.0, 4.0, 5.0);
        assertEquals(5, result);
        assertEquals(3.0, seriesAnalysis.average(), 0.00001);
    }

    @Test
    public void testAddTempsToEmpty() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        int result = seriesAnalysis.addTemps(1.0, 2.0, 3.0);
        assertEquals(3, result);
        assertEquals(2.0, seriesAnalysis.average(), 0.00001);
    }

    @Test
    public void testAddTempsEmpty() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        int result = seriesAnalysis.addTemps();
        assertEquals(2, result);
    }

    @Test(expected = InputMismatchException.class)
    public void testAddTempsWithInvalidTemperature() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        seriesAnalysis.addTemps(3.0, -300.0, 5.0);
    }

    @Test
    public void testAddTempsDoesNotAddOnException() {
        double[] temperatureSeries = {1.0, 2.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        try {
            seriesAnalysis.addTemps(3.0, -300.0, 5.0);
            fail("Expected InputMismatchException");
        } catch (InputMismatchException e) {
            assertEquals(1.5, seriesAnalysis.average(), 0.00001);
        }
    }

    @Test
    public void testAddTempsCausesExpansion() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        seriesAnalysis.addTemps(1.0);
        seriesAnalysis.addTemps(2.0);
        seriesAnalysis.addTemps(3.0, 4.0);
        seriesAnalysis.addTemps(5.0, 6.0, 7.0, 8.0);
        assertEquals(8, seriesAnalysis.addTemps(9.0));
    }

    @Test
    public void testAddTempsWithAbsoluteZero() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();
        int result = seriesAnalysis.addTemps(-273.0);
        assertEquals(1, result);
        assertEquals(-273.0, seriesAnalysis.average(), 0.00001);
    }
}
