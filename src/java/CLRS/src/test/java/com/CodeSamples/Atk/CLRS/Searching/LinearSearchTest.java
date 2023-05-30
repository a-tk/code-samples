package com.CodeSamples.Atk.CLRS.Searching;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LinearSearchTest {

    Integer[] array;
    LinearSearch<Integer> linearSearch;

    @Before
    public void setUp() throws Exception {
        array = new Integer[] {6, 2, 1, 0, -40, 100};
        linearSearch = new LinearSearch<Integer>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSearch() {

        int found = linearSearch.linearSearch(array, 6);
        assertEquals(0, found);
    }

    @Test
    public void testUpperBound() {

        int found = linearSearch.linearSearch(array, 100);
        assertEquals(5, found);
    }

    @Test
    public void testNotFound() {

        int found = linearSearch.linearSearch(array, 123);
        assertEquals(-1, found);
    }
}