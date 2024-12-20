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


# Resultats de mes tests:

## Execution Logs

### Properties File
```plaintext
Dec 20, 2024 9:23:09 PM net.jqwik.engine.JqwikProperties loadProperties
INFO: No Jqwik properties file [jqwik.properties] found.

Test 1: allSortedCorrectly
timestamp = 2024-12-20T21:23:10.433434360, SortingTest:allSortedCorrectly =
                              |------------------- jqwik -------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 10         | # of all combined edge cases
edge-cases#tried = 10         | # of edge cases tried in current run
seed = -8064259374196844114   | random seed to reproduce generated values

Test 2: allSortingMethodsAgree

timestamp = 2024-12-20T21:23:10.600854655, SortingTest:allSortingMethodsAgree =
                              |------------------- jqwik -------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 10         | # of all combined edge cases
edge-cases#tried = 10         | # of edge cases tried in current run
seed = -7733976186437203778   | random seed to reproduce generated values

