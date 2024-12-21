package fr.istic.vv;

import net.jqwik.api.*;
import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    // Vérifie que BubbleSort retourne un tableau trié
    @Property
    boolean bubbleSortReturnsSortedArray(@ForAll int[] array) {
        Integer[] input = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Integer[] sortedArray = Sorting.bubbleSort(input, Comparator.naturalOrder());
        return isSorted(sortedArray, Comparator.naturalOrder());
    }

    // Vérifie que QuickSort retourne un tableau trié
    @Property
    boolean quickSortReturnsSortedArray(@ForAll int[] array) {
        Integer[] input = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Sorting.quickSort(input, Comparator.naturalOrder());
        return isSorted(input, Comparator.naturalOrder());
    }

    // Vérifie que MergeSort retourne un tableau trié
    @Property
    boolean mergeSortReturnsSortedArray(@ForAll int[] array) {
        Integer[] input = Arrays.stream(array).boxed().toArray(Integer[]::new);
        Integer[] sortedArray = Sorting.mergeSort(input, Comparator.naturalOrder());
        return isSorted(sortedArray, Comparator.naturalOrder());
    }

    // Vérifie que les tri fonctionnent avec un tableau vide
    @Example
    void sortingEmptyArray() {
        Integer[] emptyArray = {};
        assert Sorting.bubbleSort(emptyArray, Comparator.naturalOrder()).length == 0;
        Sorting.quickSort(emptyArray, Comparator.naturalOrder());
        assert emptyArray.length == 0;
        assert Sorting.mergeSort(emptyArray, Comparator.naturalOrder()).length == 0;
    }

    // Vérifie que les tri fonctionnent avec un tableau à un seul élément
    @Example
    void sortingSingleElementArray() {
        Integer[] singleElement = {42};
        assert Arrays.equals(Sorting.bubbleSort(singleElement, Comparator.naturalOrder()), singleElement);
        Sorting.quickSort(singleElement, Comparator.naturalOrder());
        assert Arrays.equals(singleElement, new Integer[]{42});
        assert Arrays.equals(Sorting.mergeSort(singleElement, Comparator.naturalOrder()), singleElement);
    }

    // Vérifie que les tri fonctionnent avec des éléments identiques
    @Example
    void sortingIdenticalElements() {
        Integer[] identicalElements = {5, 5, 5, 5};
        assert isSorted(Sorting.bubbleSort(identicalElements, Comparator.naturalOrder()), Comparator.naturalOrder());
        Sorting.quickSort(identicalElements, Comparator.naturalOrder());
        assert isSorted(identicalElements, Comparator.naturalOrder());
        assert isSorted(Sorting.mergeSort(identicalElements, Comparator.naturalOrder()), Comparator.naturalOrder());
    }

    // Fonction utilitaire pour vérifier si un tableau est trié
    private <T> boolean isSorted(T[] array, Comparator<T> comparator) {
        for (int i = 1; i < array.length; i++) {
            if (comparator.compare(array[i - 1], array[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}
