package com.sandarovich.module1.findistance;

import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.processing.SupportedSourceVersion;

import org.hamcrest.core.SubstringMatcher;

/**
 * @author Olexander Kolodiazhny 2016
 * 
 *         Custom Array List for task #2.
 *
 */

public class MyArray {

    private ArrayList<Integer> array;
    private ArrayList<Integer> sortedArray;

    public MyArray(int[] array) {
        this.array = new ArrayList<Integer>();
        for (int i : array) {
            this.array.add(i);
        }
        this.sortedArray = new ArrayList<Integer>(this.array);
    }

    private void sort() {
        Collections.sort(this.sortedArray);

    }

    private int getElementCount(int element) {
        if (this.array.contains(element)) {
            return Collections.frequency(this.array, element);
        }
        return 0;
    }

    private int getElementIndex(int element) {
        return this.array.indexOf(element);
    }

    private ArrayList<Integer> getDublikatElementIndex(int firstMin) {

        ArrayList<Integer> indexesOfMin = new ArrayList<Integer>();

        for (int i = 0; i < this.array.size(); i++) {
            if (this.array.get(i) == firstMin) {
                indexesOfMin.add(i);
            }
        }

        return indexesOfMin;
    }

    private String getDistanceBetweenTwoElement(int firstMin, int secondMin) {
        int out = Math.abs(getElementIndex(firstMin) - getElementIndex(secondMin));
        return String.valueOf(out);
    }

    private ArrayList<Integer> getDistanceBetweenSameElement(int firstMin) {
        ArrayList<Integer> out = new ArrayList<>();
        ArrayList<Integer> indexesOfMin = getDublikatElementIndex(firstMin);

        for (int i = 0; i <= indexesOfMin.size(); i++) {
            for (int j = i + 1; j < indexesOfMin.size(); j++) {
                int firstIndex = indexesOfMin.get(i);
                int secondIndex = indexesOfMin.get(j);
                out.add(Math.abs(firstIndex - secondIndex));
            }
        }
        
        return out;
    }
    
    private String distancesToString(ArrayList<Integer> distances) {
        String out = "";
        
        for (int elem: distances) {
            out = out + elem +  ", ";
        }
        return out.substring(0, out.length() - 2);
        
    }

    // TODO O.Kolodiazhny. MAYBE PATTERN STRATEGY??? Not sure.
    
    public String getDistance() {
        this.sort();
        if (getElementCount(sortedArray.get(0)) >= 2) {
            return distancesToString(getDistanceBetweenSameElement(sortedArray.get(0)));
        } else if (getElementCount(sortedArray.get(0)) == 1) {
            return getDistanceBetweenTwoElement(sortedArray.get(0), sortedArray.get(1));
        } else
            return "-1";
    }
}
