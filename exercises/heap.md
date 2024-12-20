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


**Bugs Identified and Fixed Through Testing**

1. **Off-by-One Error in `bubbleUp`**  
   *Description:* The `bubbleUp` method did not correctly handle the parent index calculation for the newly inserted element. This led to certain elements not being properly placed after insertion, causing the heap to occasionally return an incorrect minimum.  
   *How Tests Helped:* In some cases, the heap would fail to restore the heap property after insertion. By examining the failing test inputs and the heap state just before the error occurred, I noticed that the parent-child relationship was being computed incorrectly.

2. **Incorrect Handling of Duplicates in `pop`**  
   *Description:* The original implementation did not properly handle multiple identical elements in the heap. The `pop` method sometimes returned elements in an unexpected order.  
   *How Tests Helped:* I discovered that popping from a heap containing multiple identical values occasionally produced an incorrect sequence. Inspecting the heap’s internal array during these failing runs revealed that the `bubbleDown` method needed a fix.

3. **Improper Exception Throwing on Empty Heap**  
   *Description:* Calling `pop` or `peek` on an empty heap did not consistently throw the expected `NoSuchElementException`. Sometimes, due to a missing check, it returned an undefined value.  
   *How Tests Helped:* Tests were designed to call `pop` or `peek` on an empty heap. These tests promptly failed, indicating that the code did not behave as specified.