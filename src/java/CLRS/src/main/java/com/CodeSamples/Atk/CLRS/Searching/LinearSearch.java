package com.CodeSamples.Atk.CLRS.Searching;

public class LinearSearch<T> {

    /**
     * Searches the given array for value x.
     * @param array the array to search
     * @param x the value to search for
     * @return array index of found value x, or -1 if not found
     */
    public int linearSearch(T[] array, T x) {

        int index = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(x)) {
                index = i;
                break;
            }
        }
        return index;
    }
}
