package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;

public class SortingTest {

    private static final Comparator<Integer> COMPARATEUR_ENTIERS = Integer::compareTo;


    @Property
    boolean tousLesTrisSaccordent(@ForAll("tableauxEntiersAleatoires") Integer[] tableauOriginal) {
        Integer[] copiePourBulle = Arrays.copyOf(tableauOriginal, tableauOriginal.length);
        Integer[] copiePourRapide = Arrays.copyOf(tableauOriginal, tableauOriginal.length);
        Integer[] copiePourFusion = Arrays.copyOf(tableauOriginal, tableauOriginal.length);

        Integer[] resultatBulle = Sorting.bubblesort(copiePourBulle, COMPARATEUR_ENTIERS);
        Integer[] resultatRapide = Sorting.quicksort(copiePourRapide, COMPARATEUR_ENTIERS);
        Integer[] resultatFusion = Sorting.mergesort(copiePourFusion, COMPARATEUR_ENTIERS);

        if (!Arrays.equals(resultatBulle, resultatRapide)) {
            return false;
        }

        if (!Arrays.equals(resultatBulle, resultatFusion)) {
            return false;
        }
        if (!estTrie(resultatBulle, COMPARATEUR_ENTIERS)) {
            return false;
        }
        return true;
    }
    @Provide
    Arbitrary<Integer[]> tableauxEntiersAleatoires() {
        // Génération tableaux d'entiers entre -1000 et 1000 de taille 0 à 50
        return Arbitraries.integers().between(-1000, 1000)
                .array(Integer[].class)
                .ofMinSize(0).ofMaxSize(50);
    }

    private static <T> boolean estTrie(T[] tableau, Comparator<T> comparateur) {
        for (int i = 1; i < tableau.length; i++) {
            if (comparateur.compare(tableau[i-1], tableau[i]) > 0) {
                return false;
            }
        }
        return true;
    }
}