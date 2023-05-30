package com.CodeSamples.Atk.CLRS.Sorting;

import java.util.Arrays;

public class InsertionSort implements Sort<Integer> {

    private Integer[] b;

    public InsertionSort(Integer[] a) {
        b = Arrays.copyOf(a, a.length);
    }

    public Integer[] sort () {

        if (b != null && b.length > 1) { // if length is one or less, its already sorted

            for (int j = 1; j < b.length; j++) {

                int key = b[j];
                //insert a[j] into the sorted sequence a[1 .. j - 1]
                int i = j - 1;

                while ( i >= 0 && b[i] > key ) {

                    b[ i + 1 ] = b[i];
                    i = i - 1;
                }
                b[i + 1] = key;
            }
        }
        return b;
    }

}
