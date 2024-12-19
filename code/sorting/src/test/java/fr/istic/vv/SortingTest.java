package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SortingTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll @IntRange(min=Integer.MIN_VALUE +1) int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    <T> void bubblesortIsCorrect(@ForAll List<@Size(max = 50) @IntRange(min = -1000, max = 1000) Integer> input) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[]  sortedArray = Sorting.bubblesort((T[]) input.toArray(), comparator);

        boolean result = sortedArray.length == input.size();
        for (int i = 0; i < sortedArray.length; i++) {
            if(i < sortedArray.length-1)
                result = result && comparator.compare(sortedArray[i], sortedArray[i+1]) <= 0;


        }

        List<T> inputSorted = (List<T>) input.stream().sorted().collect(Collectors.toList());
        result = inputSorted.equals(sortedArray);

        T[] resortedArray = Sorting.bubblesort(sortedArray, comparator);
        assertTrue(Arrays.equals(sortedArray, resortedArray));
    }

    @Property
    <T> void quicksortIsCorrect(@ForAll List<@Size(max = 50) @IntRange(min = -1000, max = 1000) Integer> input) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[]  sortedArray = Sorting.quicksort((T[]) input.toArray(), comparator);

        boolean result = sortedArray.length == input.size();
        for (int i = 0; i < sortedArray.length; i++) {
            if(i < sortedArray.length-1)
                result = result && comparator.compare(sortedArray[i], sortedArray[i+1]) <= 0;


        }

        List<T> inputSorted = (List<T>) input.stream().sorted().collect(Collectors.toList());
        result = inputSorted.equals(sortedArray);

        T[] resortedArray = Sorting.quicksort(sortedArray, comparator);
        assertTrue(Arrays.equals(sortedArray, resortedArray));
    }

    @Property
    <T> void mergesortIsCorrect(@ForAll List<@Size(max = 50) @IntRange(min = -1000, max = 1000) Integer> input) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[]  sortedArray = Sorting.mergesort((T[]) input.toArray(), comparator);

        boolean result = sortedArray.length == input.size();
        for (int i = 0; i < sortedArray.length; i++) {
            if(i < sortedArray.length-1)
                result = result && comparator.compare(sortedArray[i], sortedArray[i+1]) <= 0;


        }

        List<T> inputSorted = (List<T>) input.stream().sorted().collect(Collectors.toList());
        result = inputSorted.equals(sortedArray);

        T[] resortedArray = Sorting.mergesort(sortedArray, comparator);
        assertTrue(Arrays.equals(sortedArray, resortedArray));
    }
}