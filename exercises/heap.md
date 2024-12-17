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

## Answers

### Formalizing the problem.

To do property-based testing of the heap, we will first formalize its 
properties.

Let $\text{T}$ an arbitrary set with a total order $\leq_{\text{T}}$; let 
$\text{MinHeap(T)}$ be the set of minimum binary heaps over $\text{T}$. There
exists a heap $\text{empty} \in \text{MinHeap(T)}$ defined as the *empty heap*.

In the Java implementation, we use instance methods to perform operations over
a heap, which changes. In our formalizing, in order to keep track of states,
we use functions which takes as input the current heap, as well as the
associated method parameters, and returns, if the function changes the state
of the heap, the new binary heap as well as the other returned values.

We defines the following operations over the heap :

- $\texttt{push} : \text{MinHeap(T)} \times \text{T} \to\text{MinHeap(T)}$ : 
adds a new element to the heap; takes as input the heap and the element, and
returns the resulting heap.
- $\texttt{peek} : \text{MinHeap(T)} \to \text{T}$: returns the element
at the root of the heap, i.e. the smallest element in the heap.
- $\texttt{pop} : \text{MinHeap(T)} \to \text{MinHeap(T)} \times \text{T}$:
for any given heap, pops the root, i.e. the minimum element of the heap, and
returns the modified heap and the popped element.
- $\texttt{count} : \text{MinHeap(T)} \to \mathbb{N}$: returns, for any
given heap, the number of elements in the heap.

We want these function follow the following properties :

### Length properties

-   $\texttt{push}$ increase of 1 the heap size :

    $$\forall h \in \text{MinHeap(T)}. \ \forall t \in \text{T}. \ (h' = \texttt{push}(h, t)) \Longrightarrow (\texttt{count}(h') = \texttt{count}(h) + 1)$$

-   $\texttt{pop}$ on non empty list decreases of 1 the heap size :

    $$\forall h \in \text{MinHeap(T)}. \ (h \ne \text{empty} \ \wedge \ (h', t) = \texttt{pop}(h)) \Longrightarrow (\texttt{count}(h') = \texttt{count}(h) - 1)$$

These properties are verified using respectively test cases `pushIncreaseLength`
and `popNonEmptyDecreaseLength`. We also ensure `peek` implementation does not
alter the heap size using `peekNonEmptyKeepsLength`.

The example case `emptyCountIsZero` tests the property $\texttt{count}(\text{empty}) = 0$.

### Operations on empty heap.

It is not possible to pass $\text{empty}$ to $\texttt{peek}$ and $\texttt{pop}$
operations. We check that Java implementations of these methods 
throw a `NoSuchElementException` in such cases. This is checked by 
`peekEmptyException` and `popEmptyException`.

### Operations on non-empty heaps.

On non-empty heaps, we tests the following properties. We work on sequences
$t_1, t_2, ... t_n \in \text{T}, n > 0$ of already pushed objects.

-   Pop retrieves the minimum of pushed objects :
    $$\forall t_1, t_2, ... t_n \in \text{T}. \ \left(t_1, t_2, ... t_n \ \text{pushed on empty heap} \ h \wedge (h', t) = \texttt{pop}(h)\right) \Longrightarrow \left(t = \min\left(t_1, t_2, ... t_n\right)\right)$$

-   Peek retrieves the minimum of pushed objects :
    $$\forall t_1, t_2, ... t_n \in \text{T}. \ \left(t_1, t_2, ... t_n \ \text{pushed on empty heap} \ h\right) \Longrightarrow \left(\texttt{peek}(h) = \min\left(t_1, t_2, ... t_n\right)\right)$$

We check these properties by using respectively test cases `pushReplacesMinIfMinPop` and
`pushReplacesMinIfMinPeek`.

Minimum properties are respectively checked using `popNonEmptyPopsMin` and `peekNonEmptyPeeksMin`.

### Belonging properties.

We check if operations add / remove properly items in the heap.

-   Pop on non empty heap removes popped element :

    $$\forall h \in \text{MinHeap(T)}. \ (h \ne \text{empty} \wedge (h', t) = \texttt{pop}(h) \wedge \ \text{all elements are unique}) \Longrightarrow (t \notin h') $$

    and

    $$\forall h \in \text{MinHeap(T)}. \ (h \ne \text{empty} \wedge (h', t) = \texttt{pop}(h) \wedge \ \text{minimum is present two times}) \Longrightarrow (t \in h') $$

- Push adds an element :

    $$\forall h \in \text{MinHeap(T)}. \ \forall t \in \text{T}. \ (h' = \texttt{push}(h, t)) \Longrightarrow (t \in h')$$

Pop properties are checked using `popNonEmptyRemoveFromHeap` and 
`popNonEmptyBelongsToInitialHeap` ; we also test in the implementation that
`peak` does not change the list with `peekNonEmptyKeepsInHeap`.