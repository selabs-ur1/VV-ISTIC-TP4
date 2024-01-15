package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.List;

public class BinaryHeap<T> {

    private List<T> heap;

    //Represents the ordering criterion between the objects in the heap
    private Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heap = new ArrayList<>();
        this.comparator = comparator;
    }

    /**
     * @return the minimum object in the BinaryHeap and remove it
     * @throws NoSuchElementException if the BinaryHeap is empty
     */
    public T pop() throws NoSuchElementException {
        if(heap.isEmpty()){
            throw new NoSuchElementException("BinaryHeap is empty");
        }

        T minElement = heap.get(0);

        if(heap.size()>1){
            T lastElement = heap.remove(heap.size()-1);

            heap.set(0,lastElement);
            fixHeapDown(0);
        }
        else{
            heap.clear();
        }
        return minElement;
    }

    /**
     * @return the minimum object in the BinaryHeap without removing it
     * @throws NoSuchElementException if the BinaryHeap is empty
     */
    public T peek() throws NoSuchElementException {
        if(heap.isEmpty()){
            throw new NoSuchElementException("BinaryHeap is empty");
        }
        return heap.get(0);
    }

    /**
     * Add an element to the binary heap
     * @param element
     */
    public void push(T element) {
        heap.add(element);
        fixHeapUp(heap.size()-1);
    }

    /**
     * @return the number of element in the BinaryHeap
     */
    public int count() {
        return heap.size();
    }

    private void fixHeapUp(int index){
        while(index > 0 && (comparator.compare(heap.get(index), heap.get(parent(index))) < 0)){
            swap(index,parent(index));
            index = parent(index);
        }
    }

    private void fixHeapDown(int index){
        int leftChild = left(index);
        int rightChild = right(index);
        int smallest = index;

        if(leftChild < heap.size() &&
                comparator.compare(heap.get(leftChild), heap.get(smallest)) < 0){
            smallest = leftChild;
        }

        if(rightChild < heap.size() &&
                comparator.compare(heap.get(rightChild), heap.get(smallest)) < 0){
            smallest = rightChild;
        }

        if(smallest != index){
            swap(index, smallest);
            fixHeapDown(smallest);
        }
    }

    //Swap the value at index i and j of the heap
    private void swap(int i, int j){
        T tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j,tmp);
    }

    /**
     * @param i
     * @return the index of the parent node of i
     */
    private int parent(int i){
        return (i - 1)/2;
    }

    private int left(int i){ return (2*i)+1; }

    private int right(int i){ return (2*i)+2; }
}