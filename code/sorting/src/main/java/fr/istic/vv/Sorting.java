package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        T[] arrayBis = array.clone();
        int n = arrayBis.length;
        boolean swap = true;

        while (swap) {
            swap = false;
            for (int i = 1; i < n; i++) {
                if (comparator.compare(arrayBis[i - 1], arrayBis[i]) > 0) {
                    T temp = arrayBis[i - 1];
                    arrayBis[i - 1] = arrayBis[i];
                    arrayBis[i] = temp;
                    swap = true;
                }
            }
            n--;
        }
        return arrayBis;
    }

    static <T> int partition(T[] t, int m, int n, Comparator<T> comparator) {
        T pivot = t[m];
        int i = m - 1;
        int j = n + 1;

        while (true) {

            do {
                j--;
            } while (comparator.compare(t[j], pivot) > 0);
            do {
                i++;
            } while (comparator.compare(t[i], pivot) < 0);

            if (i < j) {
                T temp = t[i];
                t[i] = t[j];
                t[j] = temp;
            } else {
                return j;
            }
        }
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        T[] arrayBis = array.clone();
        quicksortRecursive(arrayBis, 0, arrayBis.length - 1, comparator);
        return arrayBis;
    }

    private static <T> void quicksortRecursive(T[] array, int m, int n, Comparator<T> comparator) {
        if (m < n) {
            int p = partition(array, m, n, comparator);
            quicksortRecursive(array, m, p, comparator);
            quicksortRecursive(array, p + 1, n, comparator);
        }
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array == null || array.length <= 1) {
            return array;
        }

        T[] arrayBis = array.clone();

        mergesortRecursive(arrayBis, 0, arrayBis.length - 1, comparator);

        return arrayBis;
    }

    private static <T> void mergesortRecursive(T[] array, int left, int right, Comparator<T> comparator) {
        if (left < right) {
            int middle = (left + right) / 2;

            mergesortRecursive(array, left, middle, comparator);
            mergesortRecursive(array, middle + 1, right, comparator);

            merge(array, left, middle, right, comparator);
        }
    }

    private static <T> void merge(T[] array, int left, int middle, int right, Comparator<T> comparator) {
        int n1 = middle - left + 1;
        int n2 = right - middle;

        T[] leftArray = Arrays.copyOfRange(array, left, middle + 1);
        T[] rightArray = Arrays.copyOfRange(array, middle + 1, right + 1);

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
