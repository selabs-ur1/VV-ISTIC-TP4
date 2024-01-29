package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private ArrayList<T> heapAsArrayList;
    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        this.heapAsArrayList = new ArrayList<>();
        this.comparator = comparator;
    }

    public T pop() {
        if (heapAsArrayList.isEmpty()) {
            throw new NoSuchElementException();
        }

        if (heapAsArrayList.size() == 1) {
            return heapAsArrayList.get(0);
        }

        T root = heapAsArrayList.get(0);
        heapAsArrayList.set(0, heapAsArrayList.get(heapAsArrayList.size()-1));
        MinHeapify(0);

        return root;
    }

    private void MinHeapify(int key) {
        int l = 2 * key + 1;
        int r = 2 * key + 2;

        int smallest = key;
        if (l < heapAsArrayList.size() && comparator.compare(heapAsArrayList.get(l), heapAsArrayList.get(smallest)) < 0) {
            smallest = l;
        }
        if (r < heapAsArrayList.size() && comparator.compare(heapAsArrayList.get(r), heapAsArrayList.get(smallest)) < 0) {
            smallest = r;
        }

        if (smallest != key) {
            T tmp = heapAsArrayList.get(key);
            heapAsArrayList.set(key, heapAsArrayList.get(smallest));
            heapAsArrayList.set(smallest, tmp);
            MinHeapify(smallest);
        }
    }

    public T peek() {
        if (heapAsArrayList.isEmpty()) throw new NoSuchElementException();
        return heapAsArrayList.get(0); }

    public void push(T element) {

        int i = heapAsArrayList.size();
        heapAsArrayList.add(element);
        while (i != 0 && comparator.compare(heapAsArrayList.get(i), heapAsArrayList.get((i -1) / 2)) < 0) {
            T tmp = heapAsArrayList.get(i);
            heapAsArrayList.set(i, heapAsArrayList.get((i - 1) / 2));
            heapAsArrayList.set(((i - 1) / 2), tmp);
            i = ((i - 1) / 2);
        }
    }

    public int count() { return heapAsArrayList.size(); }

}