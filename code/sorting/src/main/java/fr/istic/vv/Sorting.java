package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        boolean echange ;
        do{
            echange = false;
            for(int i = 1; i< array.length;i++){
                if(comparator.compare(array[i-1],array[i]) > 0){
                    T temp = array[i-1];
                    array[i-1] = array[i];
                    array[i] = temp;
                    echange = true;
                }
            }

        } while (echange);
        return array;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        quicksortRec(array,0,array.length-1,comparator);
        return array;
    }

    private static <T> void quicksortRec(T[] array, int debut, int fin, Comparator<T> comparator) {
        if(debut < fin ){
             int pivotIndex  = partition(array,debut,fin,comparator);
             quicksortRec(array,debut,pivotIndex-1,comparator);
             quicksortRec(array,pivotIndex+1,fin,comparator);
        }
    }
    private static <T> int partition(T[] array, int debut, int fin, Comparator<T> comparator) {
        T pivot = array[fin];
        int i = debut - 1;
        for (int j = debut; j < fin; j++) {
            if (comparator.compare(array[j], pivot) <= 0) {
                i++;
                T temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        T temp = array[i + 1];
        array[i + 1] = array[fin];
        array[fin] = temp;
        return i + 1;
    }


    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
        if (array.length > 1) {
            int mid = array.length/2;
            T[] gauche = Arrays.copyOfRange(array,0,mid);
            T[] droite = Arrays.copyOfRange(array,mid,array.length);

            mergesort(gauche,comparator);
            mergesort(droite,comparator);

            fusion(array,gauche,droite,comparator);
        }



        return  array;
        }
    private static <T> void fusion(T[] array, T[] gauche, T[] droite, Comparator<T> comparator) {
        int indexGauche = 0,
                indexDroite = 0,
                indexFusion = 0;

        while(indexGauche < gauche.length && indexDroite < droite.length){
            if(comparator.compare(gauche[indexGauche],droite[indexDroite]) <=0){
                array[indexFusion++] = gauche[indexGauche++];
            } else {
                array[indexFusion++] = droite[indexDroite++];
            }
        }

        //ajout du reste
        while(indexDroite < droite.length){
            array[indexFusion++] = droite[indexDroite++];
        }
        while(indexGauche < gauche.length){
            array[indexFusion++] = gauche[indexGauche++];
        }
    }



    }
