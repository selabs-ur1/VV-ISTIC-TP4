package fr.istic.vv;

import java.util.Comparator;
import java.util.List;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;

public class SortingTest {


    @Property
    boolean differentialFuzzTest(@ForAll List<@IntRange(min = -100, max = 100) Integer> inputList) {
        // list to array 
        Integer[] inputArray = inputList.toArray(new Integer[0]);
        Comparator<Integer> comparator = Integer::compare;

        // sort avec les trois algos 
        Integer[] bubbleSorted = Sorting.bubblesort(inputArray, comparator);
        Integer[] quickSorted = Sorting.quicksort(inputArray, comparator);
        Integer[] mergeSorted = Sorting.mergesort(inputArray, comparator);


        // verification de l'égalité des trois 
        return bubbleSorted.equals(quickSorted) && bubbleSorted.equals(mergeSorted);

    }
}