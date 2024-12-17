# Sorting algorithms

Implement [Bubble sort](https://en.wikipedia.org/wiki/Bubble_sort), [Quicksort](https://en.wikipedia.org/wiki/Quicksort) and [Merge sort](https://en.wikipedia.org/wiki/Merge_sort) in the `Sorting` class as indicated below. The three methods return a sorted version of the original array. The comparison between the elements of the arrays is specified with an instance of `Comparator`.

```java
class Sorting {

    public static T[] bubblesort(T[] array, Comparator<T> comparator) { ... }

    public static T[] quicksort(T[] array, Comparator<T> comparator)  { ... }

    public static T[] mergesort(T[] array, Comparator<T> comparator) { ... }

}
```

Using [jqwik](https://jqwik.net/) create a differential fuzzing strategy to test the three sorting algorithms at the same time. Create the test before any sorting implementation. Document any bug you find with the help of your tests.


**NOTE:** 
- Do not use any existing implementation, write your own code. 
- Use the provided project template as a starting point.
- In the project you can launch the tests with `mvn test`.

## Answers

**WARNING $\LaTeX$ rendering of GitHub seems kinda bad, it may be preferable to read this doc using a proper IDE (preferably using VS Code or IntelliJ)**

In order to test the previous methods, we formalize the sorting problem and what properties a sorting function should follow.

Let $\text{T}$ be a arbitrary set with a total order $\leq_{\text{T}}$, and $\text{List(T)}$ the set of lists over $\text{T}$. A sorting function $\texttt{sort} : \text{List(T)} \to \text{List(T)}$ is a function such that :

1. $\forall l \in \text{List(T)}. |l| = |\texttt{sort}(l)|$ **(preservation of size)**
2. $\forall l \in \text{List(T)}. \forall t \in l. t \in \texttt{sort}(t)$ **(preservation of elements)**
3. $\forall l \in \text{List(T)}. \forall 1 \leq i \lt |l|. \texttt{sort}(l)_{i - 1} \leq_{\text{T}} \texttt{sort}(l)_i$ **($\texttt{sort}(t)$ is ordered)**

We test with `jqwik` functions `bubblesort`, `quicksort` and `mergesort`, using arbitraries $T \subseteq \mathbb{N}$.
