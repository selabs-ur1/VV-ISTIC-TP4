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


# Answer

1. **Individual Sorting Tests:**
   - **Bubble Sort Test (`bubbleSortIsCorrect`):** Compares the output of `bubblesort` with the expected sorted array using `Arrays.sort`.
   - **Quick Sort Test (`quickSortIsCorrect`):** Compares the output of `quicksort` with the expected sorted array.
   - **Merge Sort Test (`mergeSortIsCorrect`):** Compares the output of `mergesort` with the expected sorted array.

2. **Agreement Test (`allSortsAgree`):** Ensures that all three sorting methods produce identical results for the same input array.

3. **Sorted Validation Test (`sortedArraysAreValid`):** Checks that the output arrays from each sorting method are indeed sorted according to the comparator.

*Bugs Found and Resolved*

During the testing phase, several bugs were identified and resolved with the help of the tests:

1. **Incorrect Index Handling in Quicksort**  
   *Description:* Initially, there was an error in the `partition` method of the Quicksort implementation. When choosing the pivot and swapping elements, the final placement of the pivot led to elements not being fully sorted on one side.  
   *How Tests Helped:* The differential fuzzing tests showed random failures where the Quicksort result did not match Bubble Sort or Merge Sort. By inspecting the failing inputs, I discovered that the pivot sometimes ended up in the wrong position.

2. **Stability Issues in Merge Sort**  
   *Description:* The Merge Sort implementation introduced unexpected order changes among equal elements, suggesting a stability issue. While the result remained sorted, it was sometimes in a different order from the reference sort.  
   *How Tests Helped:* The tests comparing Merge Sort directly to the built-in sorting method revealed discrepancies in the order of equivalent elements.

3. **Array Modification in Bubble Sort**  
   *Description:* Bubble Sort was initially implemented without issues, but I reused the input array directly for comparison tests. Since all tests shared the same input array instance, Quicksort and Merge Sort tests that ran afterward would receive already modified arrays. This led to confusing test failures.
