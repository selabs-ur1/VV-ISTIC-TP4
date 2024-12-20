package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    final int ARRAY_MAX_SIZE = 10;

    final Comparator<Integer> integerComparator = Integer::compareTo;
    final Comparator<String> stringComparator = String::compareTo;
    final Comparator<Double> doubleComparator = Double::compareTo;

    @Provide
    Arbitrary<Integer[]> tabIntProvider() {
        return Arbitraries.integers().array(Integer[].class).ofMaxSize(ARRAY_MAX_SIZE);
    }

    @Provide
    Arbitrary<String[]> tabStringProvider() {
        return Arbitraries.strings().array(String[].class).ofMaxSize(ARRAY_MAX_SIZE);
    }

    @Provide
    Arbitrary<Double[]> tabDoubleProvider() {
        return Arbitraries.doubles().array(Double[].class).ofMaxSize(ARRAY_MAX_SIZE);
    }


    @Property
    boolean integerArrayIsSorted(@ForAll("tabIntProvider") Integer [] tab) {
        final Integer [] quicksort = Sorting.quicksort(tab.clone(), integerComparator);
        final Integer [] bubblesort = Sorting.bubblesort(tab.clone(), integerComparator);
        final Integer [] mergesort = Sorting.mergesort(tab.clone(), integerComparator);
        return Arrays.equals(quicksort, bubblesort) && Arrays.equals(bubblesort, mergesort);
    }

    @Property
    boolean stringArrayIsSorted(@ForAll("tabStringProvider") String [] tab) {
        final String [] quicksort = Sorting.quicksort(tab.clone(), stringComparator);
        final String [] bubblesort = Sorting.bubblesort(tab.clone(), stringComparator);
        final String [] mergesort = Sorting.mergesort(tab.clone(), stringComparator);
        return Arrays.equals(quicksort, bubblesort) && Arrays.equals(bubblesort, mergesort);
    }

    @Property
    boolean doubleArrayIsSorted(@ForAll("tabDoubleProvider") Double [] tab) {
        final Double [] quicksort = Sorting.quicksort(tab.clone(), doubleComparator);
        final Double [] bubblesort = Sorting.bubblesort(tab.clone(), doubleComparator);
        final Double [] mergesort = Sorting.mergesort(tab.clone(), doubleComparator);
        return Arrays.equals(quicksort, bubblesort) && Arrays.equals(bubblesort, mergesort);
    }



}