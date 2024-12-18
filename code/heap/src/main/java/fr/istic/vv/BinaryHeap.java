package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;

public class BinaryHeap<T> {

    public int size;
    public ArrayList<T> elements;
    public Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        if(comparator == null) {
            throw new IllegalArgumentException("Comparator cannot be null");
        }
        this.comparator = comparator;
        this.size = 0;
        this.elements = new ArrayList<>();
    }

    /*returns the parent of ith Node*/
    public int parent(int i) {
        return (i - 1) / 2;
    }
    /*returns the left child of ith Node*/
    public int left(int i) {
        return 2 * i + 1;
    }
    /*Returns the right child of the ith Node*/
    public int right(int i) {
        return 2 * i + 2;
    }

    public T pop() {
        if (size <= 0) {
            //throw the exception
            throw new NoSuchElementException();
        }
        if(size ==1){
            size --;
            return elements.get(0);
        }
        T root = elements.get(0);
        elements.set(0,elements.get(size-1));
        size--;
        heapify(0);
        return root;
    }
    public void heapify(int ind) {
        int ri = right(ind); /*right child*/
        int li = left(ind); /*left child*/
        int smallest = ind; /*intially assume violated value in Min value*/
        if (li < size && comparator.compare(elements.get(li), elements.get(smallest)) < 0)
            smallest = li;
        if (ri < size && comparator.compare(elements.get(ri), elements.get(smallest)) < 0)
            smallest = ri;
        /*smallest will store the minvalue index*/
    /*If the Minimum among the three nodes is the parent itself,
    that is Heap property satisfied so stop else call function recursively on Minvalue node*/
        if (smallest != ind) {
            T temp = elements.get(ind);
            elements.set(ind, elements.get(smallest));
            elements.set(smallest, temp);
            heapify(smallest);
        }
    }

    public T peek() {
        if (size <= 0) {
            throw new NoSuchElementException();
        }
        return elements.get(0);
    }

    public void push(T element) {
        if(element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        /*Insert new element at end*/
        elements.add(element);
        int k = size; /*store the index ,for checking heap property*/
        size++; /*Increase the size*/
        /*Fix the min heap property*/
        while (k != 0 && comparator.compare(elements.get(parent(k)), elements.get(k)) > 0) {
            T temp = elements.get(parent(k));
            elements.set(parent(k), elements.get(k));
            elements.set(k, temp);
            k = parent(k);
        }
    }
    public int count() { return size; }

}