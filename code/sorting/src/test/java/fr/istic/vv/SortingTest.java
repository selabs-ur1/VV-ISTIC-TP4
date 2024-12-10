package fr.istic.vv;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;


import java.util.Arrays;

public class SortingTest {
    @Property
        boolean arrayIsSorted(@ForAll("randomArray") int[] array){
        int[] temp = array.clone();
        Arrays.sort(temp);
        Sorting.mergesort(array, 0, array.length -1);
        return Arrays.equals(temp, array);
    }



}