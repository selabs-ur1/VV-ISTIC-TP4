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
`peek` similar to `pop`, returns the minimum object, but it does not remove it from the `BinaryHeap`.
`push` adds an element to the `BinaryHeap`.
`count` returns the number of elements in the `BinaryHeap`.

With the help of [jqwik](https://jqwik.net/) create a test that generates random inputs for your heap and ensures that the element returned by `pop` every time it is invoked is the minimum of the remaining elements in the heap.


**NOTE:** 
- Do not use any existing implementation, write your own code. 
- Use the provided project template as a starting point. 
- In the project you can launch the tests with `mvn test`.
- You may reuse your binary heap code from the previous practical assignment.

## Answer

## Introduction
The task is to implement a property-based test for the `BinaryHeap` class using the `jqwik`. The BinaryHeap is a data structure similar to a priority queue, so it was chosen to use it as a basis. The goal is to verify that the `pop` method of the `BinaryHeap` class correctly returns the minimum element of the heap.

## Writing the `BinaryHeap` Class
The `BinaryHeap` class is implemented to handle generic types and utilizes a `PriorityQueue`. Key methods include `pop`, `peek`, `push`, and `count` to perform operations on the heap.

## Writing the Property-based Test
The test method `popReturnsMinimum` takes a randomly generated `HeapAndElements` object as a parameter. Inside the test method:

##### 1. Initialization
- Extract the BinaryHeap and a list of elements from the HeapAndElements object.
- Sort the list of elements in ascending order.
##### 2. Testing
- Loop through the sorted elements and compare each element with the result of the `pop` method.
- Verify that the `pop` method returns the minimum element at each step.
- Check that the count of elements in the heap decreases correctly.
##### 3. Final assertion
- Ensure that calling pop on an empty heap results in a `NoSuchElementException`.

## Generating random inputs with jqwik
The `heapAndElements` method uses `Arbitraries` to generate random instances of HeapAndElements:
- Generate a random comparator using `Arbitraries.of(Comparator.naturalOrder())`.
- Generate a list of random integers with a minimum size of 1 using `Arbitraries.integers().list().ofMinSize(1)`.
- Create a `BinaryHeap` using the generated comparator and push the elements into the heap.
- Return a new `HeapAndElements` object containing the heap and the list of elements.

## Results
Upon thorough testing using property-based testing with `jqwik`, all the tests for the `BinaryHeap.java` class have passed successfully.

![heap](images/heap.jpg)