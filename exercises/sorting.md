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

# Implémentation et tests

## Implémentation

Implémentation réalisée en suivant le pseudo code de chaque algorithme disponible sur leur page wikipédia

* Les différents algorithmes ne trie pas directement la liste passée en paramètre, ils la copient et renvoient une version triée de celle-ci.

## Propriétés testées

Les algorithmes on été testé sur des listes d'int de taille minimum 1.

* Test individuel de chaque algorithmes sur des listes d'int
* Test des 3 algorithmes sur les mêmes listes

## Bug

* Bubble sort - La boucle for, contenu dans la boucle while, utilisait une limite erroné, provoquant une boucle infinie