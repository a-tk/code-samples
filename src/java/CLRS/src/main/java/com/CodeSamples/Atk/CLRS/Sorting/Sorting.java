package com.CodeSamples.Atk.CLRS.Sorting;

import java.util.Arrays;

public class Sorting<T extends Comparable<T>> {

    /**
     * Creates a copy of array a and returns it sorted to the caller
     * @param a - array to sort
     * @return - sorted array b
     */
    public T[] insertionSort (T[] a) {

        T[] b = Arrays.copyOf(a, a.length);

        if (b.length > 1) { // if length is one or less, its already sorted

            for (int j = 1; j < b.length; j++) {

                T key = b[j];
                //insert a[j] into the sorted sequence a[1 .. j - 1]
                int i = j - 1;

                while ( i >= 0 && b[i].compareTo(key) > 0 ) {
                    b[ i + 1 ] = b[i];
                    i = i - 1;
                }
                b[i + 1] = key;
            }
        }
        return b;
    }

    public T[] bubbleSort(T[] a) {
        T[] b = Arrays.copyOf(a, a.length);

        return b;
    }

}
