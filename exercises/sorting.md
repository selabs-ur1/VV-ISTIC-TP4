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

## Answer 
J'ai eu plus de mal à implémenter les méthode de tris à cause du type générique mais la partie rédaction de test ne m'a pas posé de problèmes.

Le code est [ici](../code/sorting/src/main/java/fr/istic/vv/Sorting.java).
Le test est [ici](../code/sorting/src/test/java/fr/istic/vv/SortingTest.java).

Voici le résultat obtenus :

```bash 
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running SortingTest
timestamp = 2024-12-13T12:15:16.501331357, SortingTest:differentialFuzzTest = 
                              |-------------------jqwik-------------------
tries = 1000                  | # of calls to property
checks = 1000                 | # of not rejected calls
generation = RANDOMIZED       | parameters are randomly generated
after-failure = PREVIOUS_SEED | use the previous seed
edge-cases#mode = MIXIN       | edge cases are mixed in
edge-cases#total = 10         | # of all combined edge cases
edge-cases#tried = 10         | # of edge cases tried in current run
seed = -7668271737342850089   | random seed to reproduce generated values


Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.515 s - in SortingTest

Results:

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

------------------------------------------------------------------------
BUILD SUCCESS
------------------------------------------------------------------------
Total time:  2.612 s
Finished at: 2024-12-13T12:15:16+01:00
------------------------------------------------------------------------
```