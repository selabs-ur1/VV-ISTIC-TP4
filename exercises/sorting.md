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


## Bug trouvés en utilisant des Property Based Tests avec differencial fuzzing test :

1. Le compteur de bubblesort était inversé et la condition d'arrêt de la boucle aussi, mais pas l'initialisation de la variable i. Au lieu de compter de n-1 à 1, on ne pouvait pas rentrer dans la boucle.

    ```java
    for(int i = len-1; i <= 1; i++)
    ```
    corrigé en
    ```java
    for(int i = len-1; i >= 1; i--)
    ```

2. La phase de fusion des deux tableaux dans mergesort fusionnait correctement tous les éléments, mais arrêtait la fusion une fois un des deux tableau parcourus. Il fallait donc rajouter les éléments manquant du deuxième tableau.

    ```java
    int l = 0, r =0, index = 0;
    while(l < left.length && r < right.length){
        if(comparator.compare(left[l], right[r]) < 0){
            merge[index] = left[l];
            l++;
        } else {
            merge[index] = right[r];
            r++;
        }
        index = l + r;
    }
    ```

    corrigé en :
    ```java
    int l = 0, r =0, index = 0;
    while(l < left.length && r < right.length){
        if(comparator.compare(left[l], right[r]) < 0){
            merge[index] = left[l];
            l++;
        } else {
            merge[index] = right[r];
            r++;
        }
        index = l + r;
    }
    if (l < left.length) System.arraycopy(left, l, merge, index, left.length - l);
    if (r < right.length) System.arraycopy(right, r, merge, index, right.length - r);     
    ```