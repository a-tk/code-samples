package com.CodeSamples.Atk.CLRS;

import com.CodeSamples.Atk.CLRS.Sorting.InsertionSort;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        Integer[] a = {9, 8, 7, 6, 5, 4, 3, 2, 1 };

        System.out.println( "Original Array " + printArray(a));

        InsertionSort insertionSort = new InsertionSort(a); 
        
        System.out.println("Insertion sort: " + printArray(insertionSort.sort()));
    }

    public static String printArray(Integer[] a) {
        String printedA = "";

        if ( a == null ) {
            return "";
        }

        for (int i = 0; i < a.length; i++) {
            printedA += " " + a[i];
        }

        return printedA;
    }
}
