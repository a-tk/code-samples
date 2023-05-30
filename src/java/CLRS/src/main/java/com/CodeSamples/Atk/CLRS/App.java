package com.CodeSamples.Atk.CLRS;

import com.CodeSamples.Atk.CLRS.Sorting.Sorting;

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

        Sorting<Integer> sorting = new Sorting<Integer>();
        
        System.out.println("Insertion sort: " + printArray(sorting.insertionSort(a)));
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
