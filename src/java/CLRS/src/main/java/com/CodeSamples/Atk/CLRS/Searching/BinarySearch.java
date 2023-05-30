package com.CodeSamples.Atk.CLRS.Searching;

public class BinarySearch<T extends Comparable<T>> {

    /**
     * find the index of an object in a given array
     * @param array - The array to search
     * @param x - the object to search for
     * @param low - the lowest index to search
     * @param high - the highest index to search
     * @return - the index or -1 if not found
     */
    public int binarySearchRecursive(T[] array, T x, int low, int high) {

        if (low > high) {
            return -1;
        }
        int midpoint = (low + high) / 2; //int division will always floor this

        if (array[midpoint].equals(x)) {
            return midpoint;
        } else if (array[midpoint].compareTo(x) > 0) {
            return binarySearchRecursive(array, x, low, midpoint - 1);
        } else {
            return binarySearchRecursive(array, x, midpoint + 1, high);
        }
    }

    /**
     * find the index of an object in a given array
     * @param array - The array to search
     * @param x - the object to search for
     * @return - the index or -1 if not found
     */
    public int binarySearchIterative(T[] array, T x) {

        int low = 0;
        int high = array.length - 1;
        int midpoint;

        while (low <= high ) {
            midpoint = (low + high) / 2;

            int compare = array[midpoint].compareTo(x);

            if (compare == 0) {
                return midpoint;
            } else if (compare > 0) {
                high = midpoint - 1;
            } else {
                low = midpoint + 1;
            }
        }
        return -1;
    }
}
