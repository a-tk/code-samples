package com.CodeSamples.Atk.CLRS.Searching;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BinarySearchTest {

    Integer[] array;
    BinarySearch<Integer> binarySearch;

    @Before
    public void setUp() throws Exception {
        array = new Integer[] {1, 2, 3, 4, 5, 6};
        binarySearch = new BinarySearch<>();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSearchIterative() {

        int found = binarySearch.binarySearchIterative(array, 1);
        assertEquals(0, found);
    }

    @Test
    public void testUpperBoundIterative() {

        int found = binarySearch.binarySearchIterative(array, 6);
        assertEquals(5, found);
    }

    @Test
    public void testNotFoundIterative() {

        int found = binarySearch.binarySearchIterative(array, 123);
        assertEquals(-1, found);
    }

    @Test
    public void testSearchRecursive() {

        int found = binarySearch.binarySearchRecursive(array, 1, 0, array.length - 1);
        assertEquals(0, found);
    }

    @Test
    public void testUpperBoundRecursive() {

        int found = binarySearch.binarySearchRecursive(array, 6, 0, array.length - 1);
        assertEquals(5, found);
    }

    @Test
    public void testNotFoundRecursive() {

        int found = binarySearch.binarySearchRecursive(array, 123, 0, array.length - 1);
        assertEquals(-1, found);
    }

    @Test
    public void testBestCasePerformance() {
        Integer[] array = new Integer[100000000];
        Arrays.setAll(array, p -> p);

        long start = System.currentTimeMillis();
        binarySearch.binarySearchIterative(array, 0);
        long end = System.currentTimeMillis();

        System.out.println("Iterative binary search took " + (end - start) + " millis");

        start = System.currentTimeMillis();
        binarySearch.binarySearchRecursive(array, 0, 0, array.length - 1);
        end = System.currentTimeMillis();

        System.out.println("Recursive binary search took " + (end - start) + " millis");

        LinearSearch<Integer> lin = new LinearSearch<>();

        start = System.currentTimeMillis();
        lin.linearSearch(array, 0);
        end = System.currentTimeMillis();

        System.out.println("Linear search took " + (end - start) + " millis");
    }
    @Test
    public void testWorstCasePerformance() {
        Integer[] array = new Integer[100000000];
        Arrays.setAll(array, p -> p);

        long start = System.currentTimeMillis();
        binarySearch.binarySearchIterative(array, -1);
        long end = System.currentTimeMillis();

        System.out.println("Iterative binary search took " + (end - start) + " millis");

        start = System.currentTimeMillis();
        binarySearch.binarySearchRecursive(array, -1, 0, array.length - 1);
        end = System.currentTimeMillis();

        System.out.println("Recursive binary search took " + (end - start) + " millis");

        LinearSearch<Integer> lin = new LinearSearch<>();

        start = System.currentTimeMillis();
        lin.linearSearch(array, -1);
        end = System.currentTimeMillis();

        System.out.println("Linear search took " + (end - start) + " millis");
    }
}