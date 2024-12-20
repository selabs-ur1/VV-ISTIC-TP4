package fr.istic.vv;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {

    private class Node{
        T value;
        Node left;
        Node right;
        Node top;
        Node(T value, Node left, Node right, Node top){
            this.value = value;
            this.left = left;
            this.right = right;
            this.top = top;
        }
    }

    private Node root;
    private int count;

    private final Comparator<T> comparator;

    public BinaryHeap(Comparator<T> comparator) {
        root = new Node(null, null, null, null);
        count = 0;
        this.comparator = comparator;
    }

    private Node findMinRec(Node node){
        Node left = node.left;
        if(left == null) {
            return node;
        }
        else return findMinRec(left);
    }

    public T pop() {
        if(count == 0) throw new NoSuchElementException("Empty heap");
        if(count == 1){
            count = 0;
            return root.value;
        }
        Node min = findMinRec(root);
        if(min == root) {
            T minValue = min.value;
            root = root.right;
            count--;
            return  minValue;
        }
        T minValue = min.value;
        Node parent = min.top;
        parent.left = min.right;
        if(parent.left != null){
            parent.left.top = parent;
        }
        count--;
        return minValue;
    }

    public T peek() {
        if(count == 0) throw new NoSuchElementException("Empty heap");
        return findMinRec(root).value;
    }

    private void pushRec(Node node, T value){
        if(comparator.compare(node.value, value) > 0){
            Node left = node.left;
            if(left == null) {
                node.left = new Node(value, null, null, node);;
            }
            else pushRec(left, value);
        }else {
            Node right = node.right;
            if(right == null) node.right = new Node(value, null, null, node);
            else pushRec(right, value);
        }
    }

    public void push(T element) {
        if(count == 0){
            root.value = element;
        }
        else pushRec(root, element);
        count++;
    }

    public int count() { return count; }

}