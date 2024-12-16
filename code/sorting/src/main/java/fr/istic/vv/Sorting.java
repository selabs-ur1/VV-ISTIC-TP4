package fr.istic.vv;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) {
        int len = array.length;
        T[] res = Arrays.copyOf(array, len);
        for(int i = len-1; i >= 1; i--){
            for(int j = 0; j < i; j++){
                if(comparator.compare(res[j], res[j+1]) > 0){
                    T tmp = res[j];
                    res[j] = res[j+1];
                    res[j+1] = tmp;
                }
            }
        }
        return res;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  {
        T[] copy = Arrays.copyOf(array, array.length);
        Arrays.sort(copy, comparator);
        return copy;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) { //we can optimize without making so many copies
        int len = array.length;
        T[] merge = Arrays.copyOf(array, len); //can't do new T[n] without its real class in java...
        if(len <= 1){
            return merge;
        }
        int mid = len/2;
        T[] left = mergesort(Arrays.copyOfRange(array, 0, mid), comparator);
        T[] right = mergesort(Arrays.copyOfRange(array, mid, len), comparator);
        int l = 0, r =0, index = 0;
        while(l < left.length && r < right.length){
            if(comparator.compare(left[l], right[r]) < 0){
                merge[index] = left[l];
                l++;
            } else {
                merge[index] = right[r];
                r++;
            }
            index = l + r;
        }
        if (l < left.length) System.arraycopy(left, l, merge, index, left.length - l);
        if (r < right.length) System.arraycopy(right, r, merge, index, right.length - r);
        return merge;
    }

}
