package fr.istic.vv;

import java.util.Comparator;

public class Sorting {

    /**
     * Tri à bulle
     * @param array Tableau à trier
     * @param comparator Comparateur
     * @return un tableau trié
     * @param <T>
     */
    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length <= 1) {
            return array;
        }

        boolean sorted;
        for (int i = 0; i < array.length - 1; i++) {
            sorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = false;
                }
            }
            if (sorted) {
                break;
            }
        }
        return array;
    }

    /**
     * Tri rapide
     * @param array Tableau à trier
     * @param comparator Comparateur
     * @return Un tableau trié
     * @param <T>
     */
    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length <= 1) {
            return array;
        }

        quicksort(array, 0, array.length - 1, comparator);
        return array;
    }

    /**
     * Séléction du pivot
     * @param array Sous tableau à trier
     * @param low indice bas
     * @param high indice haut
     * @param comparator Comparateur
     * @param <T>
     */
    private static <T> void quicksort(T[] array, int low, int high, Comparator<T> comparator) {
        if (low < high) {
            int pivotIndex = partition(array, low, high, comparator);
            quicksort(array, low, pivotIndex - 1, comparator);
            quicksort(array, pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Tri dans le sous tableau à l'aide du pivot
     * @param array Sous tableau à trier
     * @param low indice bas
     * @param high indice haut
     * @param comparator Comparateur
     * @return Un sous tableau trié
     * @param <T>
     */
    private static <T> int partition(T[] array, int low, int high, Comparator<T> comparator) {
        T pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        T temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    /**
     * Tri fusion
     * @param array Un tableau à trier
     * @param comparator Comparateur
     * @return Un tableau trié
     * @param <T>
     */
    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length <= 1) {
            return array;
        }

        mergesort(array, 0, array.length - 1, comparator);
        return array;
    }

    /**
     * Découpage du tableau en sous tableaux
     * @param array Un tableau à trier
     * @param left Indice bas
     * @param right Indice haut
     * @param comparator Comparateur
     * @param <T>
     */
    private static <T> void mergesort(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            mergesort(array, left, mid, comparator);
            mergesort(array, mid + 1, right, comparator);

            merge(array, left, mid, right, comparator);
        }
    }

    /**
     * Fusion des sous tableaux
     * @param array Un tableau à trier
     * @param left Indice bas
     * @param mid Indice du milieu
     * @param right Indice haut
     * @param comparator Comparateur
     * @param <T>
     */
    private static <T> void merge(T[] array, int left, int mid, int right, Comparator<T> comparator) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        T[] leftArray = (T[]) new Object[n1];
        T[] rightArray = (T[]) new Object[n2];

        System.arraycopy(array, left, leftArray, 0, n1);
        System.arraycopy(array, mid + 1, rightArray, 0, n2);

        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (comparator.compare(leftArray[i], rightArray[j]) <= 0) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }

        while (i < n1) {
            array[k++] = leftArray[i++];
        }

        while (j < n2) {
            array[k++] = rightArray[j++];
        }
    }


}
