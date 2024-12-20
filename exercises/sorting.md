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

## Bugs Found

### Bubble Sort

**Bug:** The `bubblesort` method failed to handle duplicate values correctly, causing incorrect sorting results.

**Fix:** Added a `swapped` flag to terminate early if no elements were swapped in an iteration, ensuring the algorithm handles duplicate values correctly.

```java
public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
    for (int i = 0; i < array.length - 1; i++) {
        boolean swapped = false;
        for (int j = 0; j < array.length - 1 - i; j++) {
            if (comparator.compare(array[j], array[j + 1]) > 0) {
                T temp = array[j];
                array[j] = array[j + 1];
                array[j + 1] = temp;
                swapped = true;
            }
        }
        if (!swapped) break;
    }
    return array;
}
```