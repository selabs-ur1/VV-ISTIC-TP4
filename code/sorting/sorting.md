# Sorting algorithms

# Writing sorting code 
In this assignment, we needed to implement bubble sort, quick sort and merge sort in the Sorting class. These three methods return a sorted version of the original array. The comparison of array elements is specified by the Comparator instance.

Initially a program was written that implements the sorting methods and checks this in the main function. After this programme was debugged we started writing tests. 

Next, we proceeded to create a differential phasing strategy to simultaneously test three sorting algorithms using jqwik.

## Writing the code of the list generator  

As a first step, we decided to write the ``intLists`` method. It implements in itself a generator of lists of integers, ranging in size from 5 to 20 elements, with the possibility of duplicating elements. After generation, this method sorts each generated list.  

## After generating random values for the list, we started writing tests.

1. `testBubbleSort:`

Validates the correctness of the Bubble Sort algorithm.
Generates a random list of integers and sorts it using Bubble Sort.
Then uses Collections.sort() to obtain the expected sorted list.
Checks that the result of Bubble Sort matches the expected sorted list.

2. `testQuickSort:`

Validates the correctness of the Quick Sort algorithm.
Similar to testBubbleSort, but uses Quick Sort.

3. `testMergeSort:`

Validates the correctness of the Merge Sort algorithm.
Similar to the previous tests but uses Merge Sort.

4. `testSortingAlgorithms:`

Ensures that all three sorting algorithms produce identical results on the same input list.
Generates a random list, applies all three sorting algorithms, and then checks that the resulting lists are identical.

5. `testAlreadySortedArray:`

Checks the correctness of algorithms on an already sorted array.
First sorts a random list, then applies sorting algorithms and verifies that the results match.

6. `testReverseSortedArray:`

Checks the correctness of algorithms on a reversely sorted array.
Sorts a random list in reverse order, then applies sorting algorithms and verifies that the results match.

7. `testDuplicateValues:`

Checks the correctness of algorithms when duplicates exist in the input list.
Adds duplicates to a random list, applies sorting algorithms, and then checks that the results match.

8. `testRandomOrderArray:`

Checks the correctness of algorithms on a randomly unordered array.
Shuffles a random list, applies sorting algorithms, and then checks that the results match.

9. `testEmptyListSorting:`

Checks the correctness of algorithms on an empty list.
Creates empty lists, applies sorting algorithms, and then verifies that the results are also empty lists.
