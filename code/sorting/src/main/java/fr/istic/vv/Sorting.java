package fr.istic.vv;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Sorting {

    public static <T>  T[] bubblesort(T[] array, Comparator<T> comparator) { 
    	
    	if(array == null || comparator == null)
    		return null;
    	
    	//nouvelle instance pour respecter la specif
    	T[] result = array.clone();
    	
    	if(array.length <= 1)
    		return result;
    	
    	boolean swapped = true;
    	
    	while(swapped) {
    		swapped = false;
    		for(int i=0 ; i < result.length-1 ; i++) 
    			if(comparator.compare(result[i], result[i+1]) > 0) {
    				T tmp = result[i];
    				result[i] = result[i+1];
    				result[i+1] = tmp;
    				swapped = true;
    			}
    		
    	}
    	return result;
    }

    public static <T> T[] quicksort(T[] array, Comparator<T> comparator)  { 
    	if(array == null || comparator == null)
    		return null;
    	
    	T[] result = array.clone();
    	
    	if(array.length <= 1 )
    		return result;
    	
    	quickSortRec(result, 0, result.length-1, comparator);
    	return result;
    }
    
    private static <T> void quickSortRec(T[] array, int begin, int end, Comparator<T> comparator) {
    	if(begin < end) {
	    	int pivotIndex = organiseAroundPivot(array, begin, end-1, end, comparator);
	    	quickSortRec(array, begin, pivotIndex-1, comparator);
	    	quickSortRec(array, pivotIndex+1, end, comparator);
    	}
    	
    }
    /*
     * on suppose quon prend le pivot toujours a la fin, nayant pas implementé de strategie pour le pivot
     * Si on avait une strategie pour le pivot, il faudrait sortir le pivot dentre begin et end pour que cela fonctionne
     */
    private static <T> int organiseAroundPivot(T[] array, int begin, int end, int pivotIndex, Comparator<T> comparator) {	
    	T pivot = array[pivotIndex];
    	int finalIndex = begin - 1;
    	
    	for(int i=begin ; i <= end ; i++) {
    		if(comparator.compare(array[i], pivot) < 0) {
    			finalIndex++;
    			swap(array,i,finalIndex);
    		}
    	}
    	finalIndex++;
    	//pour le cas ou begin = end, ou que le pivot etait deja bien placé
    	//if(comparator.compare(array[finalIndex], pivot) > 0) {
    		swap(array,finalIndex,pivotIndex);
        	return finalIndex;
    	//}
    	//return pivotIndex;
    }
    
    private static <T> void swap(T[]array,int i, int j) {
    	T tmp = array[i];
    	array[i] = array[j];
    	array[j] = tmp;
    }

    public static <T> T[] mergesort(T[] array, Comparator<T> comparator) { 
    	
    	if(array == null || comparator == null)
    		return null;
    	
    	if(array.length <= 1)
    		return array.clone();
    	
    	int middle = (array.length)/2;
    	T[] leftArray = Arrays.copyOfRange(array, 0, middle);
    	T[] rightArray = Arrays.copyOfRange(array, middle, array.length);
    	T[] sortedLeft = mergesort(leftArray, comparator);
    	T[] sortedRight = mergesort(rightArray, comparator);
    	
    	return merge(sortedLeft, sortedRight, comparator);
    	
    }
    

    
    private static <T> T[] merge(T[] arrayLeft, T[] arrayRight,  Comparator<T> comparator){
    	
    	@SuppressWarnings("unchecked")
		T[] result = (T[]) Array.newInstance(arrayLeft.getClass().getComponentType(), arrayLeft.length + arrayRight.length);
    	int iLeft = 0, iRight = 0;
    	
    	while(iLeft < arrayLeft.length && iRight < arrayRight.length) {
    		
    		if(comparator.compare(arrayLeft[iLeft], arrayRight[iRight]) < 0) {
    			result[iLeft + iRight] = arrayLeft[iLeft];
    			iLeft++;
    		}
    		else {
    			result[iLeft + iRight] = arrayRight[iRight];
    			iRight++;
    		}
    	}
    	
    	if(iLeft < iRight)
    		for(int i=iLeft ; i < arrayLeft.length ; i++)
    			result[iRight + i] = arrayLeft[i];
    			
    	else
    		for(int i=iRight ; i < arrayRight.length ; i++)
    			result[iLeft + i] = arrayRight[i];
    		
    	return result;
    }

}
