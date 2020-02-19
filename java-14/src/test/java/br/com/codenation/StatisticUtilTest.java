package br.com.codenation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatisticUtilTest {

    @Test
    public void testAverage() {
        assertEquals(3, StatisticUtil.average(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testMode() {
        assertEquals(3, StatisticUtil.mode(new int[]{1, 2, 3, 3}));
    }

    @Test
    public void testMedian() {
        assertEquals(3, StatisticUtil.median(new int[]{1, 2, 3, 4, 5}));
    }

    @Test
    public void testMedian2() {
        assertEquals(8, StatisticUtil.median(new int[]{1, 4, 6, 10, 12, 14}));
    }
}
