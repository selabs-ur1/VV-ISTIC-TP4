package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(array, array.length);
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < result.length - 1; i++) {
                if (comparator.compare(result[i], result[i + 1]) > 0) {
                    T temp = result[i];
                    result[i] = result[i + 1];
                    result[i + 1] = temp;
                    swapped = true;
                }
            }
        } while (swapped);
        return result;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(array, array.length);
        if (result.length < 2) return result;

        Stack<int[]> stack = new Stack<>();
        stack.push(new int[] {0, result.length - 1});
        while (!stack.isEmpty()) {
            int[] range = stack.pop();
            int low = range[0];
            int high = range[1];
            if (low < high) {
                T pivot = result[high];
                int i = low - 1;
                for (int j = low; j < high; j++) {
                    if (comparator.compare(result[j], pivot) >= 0) {
                        i++;
                        T temp = result[i];
                        result[i] = result[j];
                        result[j] = temp;
                    }
                }
                T temp = result[i + 1];
                result[i + 1] = result[high];
                result[high] = temp;
                int pivotIndex = i + 1;
                stack.push(new int[] {low, pivotIndex - 1});
                stack.push(new int[] {pivotIndex + 1, high});
            }
        }
        return result;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        T[] result = Arrays.copyOf(array, array.length);
        int n = result.length;
        for (int size = 1; size < n; size = size * 2) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * size) {
                int mid = Math.min(leftStart + size - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * size - 1, n - 1);
                int n1 = mid - leftStart + 1;
                int n2 = rightEnd - mid;
                T[] leftArr = (T[]) new Object[n1];
                T[] rightArr = (T[]) new Object[n2];

                for (int i = 0; i < n1; i++)
                    leftArr[i] = result[leftStart + i];
                for (int j = 0; j < n2; j++)
                    rightArr[j] = result[mid + 1 + j];

                int i = 0, j = 0, k = leftStart;
                while (i < n1 && j < n2) {
                    if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                        result[k] = leftArr[i];
                        i++;
                    } else {
                        result[k] = rightArr[j];
                        j++;
                    }
                    k++;
                }

                while (i < n1) {
                    result[k] = leftArr[i];
                    i++;
                    k++;
                }

                while (j < n2) {
                    result[k] = rightArr[j];
                    j++;
                    k++;
                }
            }
        }
        return result;
    }

}
