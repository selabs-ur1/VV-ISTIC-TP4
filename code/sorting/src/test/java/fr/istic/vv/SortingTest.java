package fr.istic.vv;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

public class SortingTest {

    // Shared

    private static <T> boolean isSorted(T[] array, Comparator<T> comparator) {
        return IntStream.range(0, array.length - 1)
                .allMatch(i -> comparator.compare(array[i], array[i + 1]) <= 0);
    }

    // bubbleSort

    @Property
    void bubbleSortEmptyArrayShouldStayEmpty() {
        Integer[] emptyArray = {};
        Integer[] result = Sorting.bubbleSort(emptyArray, Integer::compareTo);
        Assume.that(result.length == 0);
        Assume.that(result == emptyArray);
    }

    @Property
    void bubbleSortSingleElementArrayShouldStayUnchanged(@ForAll int element) {
        Integer[] singleElementArray = { element };
        Integer[] result = Sorting.bubbleSort(singleElementArray, Integer::compareTo);
        Assume.that(result.length == 1);
        Assume.that(result[0] == element);
    }

    @Property
    void bubbleSortSortedArrayShouldStaySorted(@ForAll List<@IntRange(min = -100, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.bubbleSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void bubbleSortUnsortedArrayShouldBecomeSorted(@ForAll List<@IntRange(min = -100, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.bubbleSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void bubbleSortArrayWithDuplicatesShouldSortProperly(@ForAll List<@IntRange(min = -10, max = 10) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.bubbleSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void bubbleSortCustomComparatorShouldWork(@ForAll List<@IntRange(min = 0, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        Integer[] result = Sorting.bubbleSort(array, reverseComparator);
        Assume.that(isSorted(result, reverseComparator));
    }

    @Example
    void bubbleSortNullArrayShouldThrowException() {
        boolean exceptionThrown = false;
        try {
            Sorting.bubbleSort(null, Integer::compareTo);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }

    @Example
    void bubbleSortNullComparatorShouldThrowException() {
        Integer[] array = { 1, 2, 3 };
        boolean exceptionThrown = false;
        try {
            Sorting.bubbleSort(array, null);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }

    @Property(tries = 10)
    void bubbleSortLargeArrayShouldBeSorted() {
        int size = 1000;
        Integer[] array = IntStream.range(0, size)
                .map(i -> (int) (Math.random() * size))
                .boxed()
                .toArray(Integer[]::new);

        Integer[] result = Sorting.bubbleSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    // QuickSort

    @Property
    void quickSortEmptyArrayShouldStayEmpty() {
        Integer[] emptyArray = {};
        Integer[] result = Sorting.quickSort(emptyArray, Integer::compareTo);
        Assume.that(result.length == 0);
        Assume.that(result == emptyArray);
    }

    @Property
    void quickSortSingleElementArrayShouldStayUnchanged(@ForAll int element) {
        Integer[] singleElementArray = { element };
        Integer[] result = Sorting.quickSort(singleElementArray, Integer::compareTo);
        Assume.that(result.length == 1);
        Assume.that(result[0] == element);
    }

    @Property
    void quickSortSortedArrayShouldStaySorted(@ForAll List<@IntRange(min = -100, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.quickSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void quickSortUnsortedArrayShouldBecomeSorted(@ForAll List<@IntRange(min = -100, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.quickSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void quickSortArrayWithDuplicatesShouldSortProperly(@ForAll List<@IntRange(min = -10, max = 10) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.quickSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void quickSortCustomComparatorShouldWork(@ForAll List<@IntRange(min = 0, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        Integer[] result = Sorting.quickSort(array, reverseComparator);
        Assume.that(isSorted(result, reverseComparator));
    }

    @Example
    void quickSortNullArrayShouldThrowException() {
        boolean exceptionThrown = false;
        try {
            Sorting.quickSort(null, Integer::compareTo);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }

    @Example
    void quickSortNullComparatorShouldThrowException() {
        Integer[] array = { 1, 2, 3 };
        boolean exceptionThrown = false;
        try {
            Sorting.quickSort(array, null);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }

    @Property(tries = 10)
    void quickSortLargeArrayShouldBeSorted() {
        int size = 1000;
        Integer[] array = IntStream.range(0, size)
                .map(i -> (int) (Math.random() * size))
                .boxed()
                .toArray(Integer[]::new);

        Integer[] result = Sorting.quickSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    // Mergesort

    @Property
    void mergeSortEmptyArrayShouldStayEmpty() {
        Integer[] emptyArray = {};
        Integer[] result = Sorting.mergeSort(emptyArray, Integer::compareTo);
        Assume.that(result.length == 0);
        Assume.that(result == emptyArray);
    }

    @Property
    void mergeSortSingleElementArrayShouldStayUnchanged(@ForAll int element) {
        Integer[] singleElementArray = { element };
        Integer[] result = Sorting.mergeSort(singleElementArray, Integer::compareTo);
        Assume.that(result.length == 1);
        Assume.that(result[0] == element);
    }

    @Property
    void mergeSortSortedArrayShouldStaySorted(@ForAll List<@IntRange(min = -100, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.mergeSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void mergeSortUnsortedArrayShouldBecomeSorted(@ForAll List<@IntRange(min = -100, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.mergeSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void mergeSortArrayWithDuplicatesShouldSortProperly(@ForAll List<@IntRange(min = -10, max = 10) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Integer[] result = Sorting.mergeSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }

    @Property
    void mergeSortCustomComparatorShouldWork(@ForAll List<@IntRange(min = 0, max = 100) Integer> list) {
        Integer[] array = list.toArray(new Integer[0]);
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        Integer[] result = Sorting.mergeSort(array, reverseComparator);
        Assume.that(isSorted(result, reverseComparator));
    }

    @Example
    void mergeSortNullArrayShouldThrowException() {
        boolean exceptionThrown = false;
        try {
            Sorting.mergeSort(null, Integer::compareTo);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }

    @Example
    void mergeSortNullComparatorShouldThrowException() {
        Integer[] array = { 1, 2, 3 };
        boolean exceptionThrown = false;
        try {
            Sorting.mergeSort(array, null);
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }
        Assume.that(exceptionThrown);
    }

    @Property(tries = 10)
    void mergeSortLargeArrayShouldBeSorted() {
        int size = 1000;
        Integer[] array = IntStream.range(0, size)
                .map(i -> (int) (Math.random() * size))
                .boxed()
                .toArray(Integer[]::new);

        Integer[] result = Sorting.mergeSort(array, Integer::compareTo);
        Assume.that(isSorted(result, Integer::compareTo));
    }
}