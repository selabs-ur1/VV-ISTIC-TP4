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


## Bug trouvés en utilisant des Property Based Tests :

1. La classe utilisait un attribut min, permettant de ne pas avoir à calculer le noeud minimum à chaque fois. Mais cela n'est pas possible à moins de rajouter plus d'attributs à la classe. Nous sommes donc repartit sur un algorithme simple et avons donc créé une méthode permettant de calculer le noeud minimal.
Le test par propriété nous à permis de prouvé que la valeur minimale n'était pas correcte sur des cas plus larges.

    Correction :
    ```java
    private Node findMinRec(Node node){
        Node left = node.left;
        if(left == null) {
            return node;
        }
        else return findMinRec(left);
    }
    ```

2. Le pop ne refaisais plus le lien vers son parent après un pop :
    ```java
    T minValue = min.value;
    Node parent = min.top;
    parent.left = min.right;
    count--;
    return minValue;
    ```
    correction :
    ```java
    T minValue = min.value;
    Node parent = min.top;
    parent.left = min.right;
    if(parent.left != null){
        parent.left.top = parent;
    }
    count--;
    return minValue;
    ```

3. On avait oublié de gérer le cas où l'élément à retirer est le parent :

    ```java
    Node min = findMinRec(root);
    T minValue = min.value;
    Node parent = min.top;
    parent.left = min.right;
    if(parent.left != null){
        parent.left.top = parent;
    }
    count--;
    return minValue;
    ```
    corrigé en :
    ```java
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
    ```
