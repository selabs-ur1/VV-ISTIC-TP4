package fr.istic.vv;

import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (comparator.compare(array[j], array[j + 1]) > 0) {
                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
        int debut = 0;
        int fin = array.length - 1;
        quicksortaux(array, comparator, debut, fin);
        return array;
    }

    private static <T> void quicksortaux(T[] array, Comparator<T> comparator, int debut, int fin) {
        if (debut < fin) {
            int pivot = part(array, comparator, debut, fin);
            quicksortaux(array, comparator, debut, pivot - 1);
            quicksortaux(array, comparator, pivot + 1, fin);
        }
    }

    private static <T> int part(T[] array, Comparator<T> comparator, int debut, int fin) {
        int pivot = debut;
        for (int i = debut + 1; i <= fin; i++) {
            if (comparator.compare(array[i], array[debut]) < 0) {
                pivot++;
                T temp = array[pivot];
                array[pivot] = array[i];
                array[i] = temp;
            }
        }
        T temp = array[debut];
        array[debut] = array[pivot];
        array[pivot] = temp;
        return pivot;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparateur) {
        if (array.length <= 1) {
            return array;
        }

        int milieu = array.length / 2;
        T[] gauche = Arrays.copyOfRange(array, 0, milieu);
        T[] droite = Arrays.copyOfRange(array, milieu, array.length);

        gauche = mergesort(gauche, comparateur);
        droite = mergesort(droite, comparateur);

        return fusionner(gauche, droite, comparateur);
    }

    private static <T> T[] fusionner(T[] gauche, T[] droite, Comparator<T> comparator) {
        T[] resultat = Arrays.copyOf(gauche, gauche.length + droite.length);
        int i = 0, j = 0, k = 0;

        while (i < gauche.length && j < droite.length) {
            if (comparator.compare(gauche[i], droite[j]) <= 0) {
                resultat[k++] = gauche[i++];
            } else {
                resultat[k++] = droite[j++];
            }
        }

        while (i < gauche.length) {
            resultat[k++] = gauche[i++];
        }

        while (j < droite.length) {
            resultat[k++] = droite[j++];
        }

        return resultat;
    }
}