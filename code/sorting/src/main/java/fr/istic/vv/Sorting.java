package fr.istic.vv;

import java.util.Comparator;

public class Sorting {

    // Bubble Sort: Optimisé avec un mécanisme de détection des échanges
    public static <T> T[] bubbleSort(T[] array, Comparator<T> comparator) {
        T[] sortedArray = array.clone();
        int n = sortedArray.length;
        boolean swapped;

        // Effectuer des passes jusqu'à ce qu'il n'y ait plus d'échanges
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (comparator.compare(sortedArray[j], sortedArray[j + 1]) > 0) {
                    swap(sortedArray, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped) break; // Si aucun échange, le tableau est déjà trié
        }
        return sortedArray;
    }

    // Quick Sort: Utilisation d'un pivot avec partitionnement classique
    public static <T> void quickSort(T[] array, Comparator<T> comparator) {
        quickSort(array, 0, array.length - 1, comparator);
    }

    // Quick Sort avec indices de début et de fin pour la récursion
    private static <T> void quickSort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quickSort(array, low, pivotIndex - 1, comparator);
            quickSort(array, pivotIndex + 1, high, comparator);
        }
    }

    // Partitionnement de l'array pour le QuickSort
    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) < 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);  // Placer le pivot à sa position correcte
        return i + 1;
    }

    // Méthode d'échange pour QuickSort et autres algorithmes
    private static <T> void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Merge Sort: Tri par fusion avec gestion des sous-tableaux
    public static <T> T[] mergeSort(T[] array, Comparator<T> comparator) {
        if (array.length <= 1) {
            return array;
        }
        T[] sortedArray = array.clone();
        mergeSortRecursive(sortedArray, comparator);
        return sortedArray;
    }

    // Récursivement diviser et fusionner le tableau
    private static <T> void mergeSortRecursive(T[] array, Comparator<T> comparator) {
        int length = array.length;
        if (length < 2) return;

        int mid = length / 2;
        @SuppressWarnings("unchecked")
        T[] left = (T[]) new Object[mid];
        @SuppressWarnings("unchecked")
        T[] right = (T[]) new Object[length - mid];

        System.arraycopy(array, 0, left, 0, mid);
        System.arraycopy(array, mid, right, 0, length - mid);

        mergeSortRecursive(left, comparator);
        mergeSortRecursive(right, comparator);
        merge(array, left, right, comparator);
    }

    // Fusionner deux sous-tableaux triés
    private static <T> void merge(T[] array, T[] left, T[] right, Comparator<T> comparator) {
        int leftIndex = 0, rightIndex = 0, arrayIndex = 0;

        // Fusionner les éléments triés des deux sous-tableaux dans array
        while (leftIndex < left.length && rightIndex < right.length) {
            if (comparator.compare(left[leftIndex], right[rightIndex]) <= 0) {
                array[arrayIndex++] = left[leftIndex++];
            } else {
                array[arrayIndex++] = right[rightIndex++];
            }
        }

        // Si des éléments restent dans left
        while (leftIndex < left.length) {
            array[arrayIndex++] = left[leftIndex++];
        }

        // Si des éléments restent dans right
        while (rightIndex < right.length) {
            array[arrayIndex++] = right[rightIndex++];
        }
    }
}
