# Testing the heap property

A [*binary heap*](https://en.wikipedia.org/wiki/Binary_heap) is a data structure that contains comparable objects and it is able to efficiently return the lowest element.
This data structure relies on a binary tree to keep the insertion and deletion operations efficient. It is the base of the [*Heapsort* algorithm](https://en.wikipedia.org/wiki/Heapsort).

Implement a `BinaryHeap` class with the following interface:

```java
class BinaryHeap<T> {

    public BinaryHeap(Comparator<T> comparator) { ... }

    public T pop() { ... }

    public T peek() { ... }

    public void push(T element) { ... }

    public int count() { ... }

}
```

A `BinaryHeap` instance is created using a `Comparator` object that represents the ordering criterion between the objects in the heap.
`pop` returns and removes the minimum object in the heap. If the heap is empty it throws a `NotSuchElementException`.
`peek` similar to `pop`, returns the minimum object but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

With the help of [jqwik](https://jqwik.net/) create a test that generates random inputs for your heap and ensures that the element returned by `pop` every time it is invoked is the minimum of the remaining elements in the heap.


**NOTE:** 
- Do not use any existing implementation, write your own code. 
- Use the provided project template as a starting point. 
- In the project you can launch the tests with `mvn test`.
- You may reuse your binary heap code from the previous practical assignment.

## Answer

J'ai bien implémenté la classe. Pour les tests, j'ai comparé la valeur sortie du heap avec les éléments d'une list contenant les même élements triés en utilisant Collections.sort. Je compares les premiers élements de chaque list avant de les supprimer. Je vérifie également que la valeur sortante de la heap est bien inférieure à la nouvelle racine de la heap.

Le code est [ici](../code/heap/src/main/java/fr/istic/vv/BinaryHeap.java).
Le test est [ici](../code/heap/src/test/java/fr/istic/vv/BinaryHeapTest.java).

```bash 
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.498 s - in BinaryHeapTest

Results:

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  3.215 s
Finished at: 2024-12-13T21:19:05+01:00
------------------------------------------------------------------------
```
