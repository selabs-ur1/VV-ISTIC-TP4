package fr.istic.vv;

public class NoSuchElementException extends RuntimeException {
    public NoSuchElementException() {
        super("No element in the heap");
    }
}
