package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    public static <T> List<T> bubblesort(List<T> array, Comparator<T> comparator) {
        int length = array.size();

        List<T> result = new ArrayList<>(array);

        boolean swapped;

        do {
            swapped = false;
            for (int i = 1; i < length; i++)
                if (comparator.compare(result.get(i - 1), result.get(i)) > 0) {
                    T tmp = result.get(i);
                    result.set(i, result.get(i - 1));
                    result.set(i - 1, tmp);
                    swapped = true;
                }
        } while (swapped);

        return result;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        return null;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        return null;
    }

}
