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

# Tests

## Propriété testée

* Contrôle que la pop renvoie bien le plus petit éléments de tous les éléments encore présent dans le BinaryHeap
  * Création d'un BinaryHeap à partir d'une liste aléatoire d'entier de taille minimum 1
  * Trie de la liste
  * Parcours de la liste triée et on contrôle du plus petit élément retourné par pop

## Bug

* L'appel de pop() sur un heap de taille 1 provoquait une exception out of bound
  * Dans l'implémentation de la méthode on retirait le dernier élément et on retirait sa valeur avec heap.remove(heap.size()-1) et on réalisait un set à l'index 0 de la valeur du dernier élément
  * Correction en ajoutant un cas particulier lorsqu'on appelle pop sur un heap de taille 1