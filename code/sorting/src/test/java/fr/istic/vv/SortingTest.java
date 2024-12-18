package fr.istic.vv;

import net.jqwik.api.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SortingTest {

    @Provide
    Arbitrary<Integer[]> randomIntegerArray() {
        return Arbitraries
                .integers()
                .array(Integer[].class)
                .ofMinSize(0)
                .ofMaxSize(100);
    }

    @Provide
    Arbitrary<String[]> randomStringArray() {
        return Arbitraries
                .strings()
                .withCharRange('a', 'z')
                .array(String[].class)
                .ofMinSize(0)
                .ofMaxSize(50);
    }

    @Provide
    Arbitrary<TestObject[]> randomObjectArray() {
        return Arbitraries
                .integers()
                .array(Integer[].class)
                .map(array -> Arrays.stream(array).map(TestObject::new).toArray(TestObject[]::new));
    }


    @Property
    <T> void differentialSortingMethodInteger(@ForAll("randomIntegerArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[] bubbleSorted = Sorting.bubblesort(array.clone(), comparator);
        T[] quickSorted = Sorting.quicksort(array.clone(), comparator);
        T[] mergeSorted = Sorting.mergesort(array.clone(), comparator);

        assertArrayEquals(bubbleSorted, quickSorted);
        assertArrayEquals(bubbleSorted, mergeSorted);
    }

    @Property
    <T> void differentialSortingMethodString(@ForAll("randomStringArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[] bubbleSorted = Sorting.bubblesort(array.clone(), comparator);
        T[] quickSorted = Sorting.quicksort(array.clone(), comparator);
        T[] mergeSorted = Sorting.mergesort(array.clone(), comparator);

        assertArrayEquals(bubbleSorted, quickSorted);
        assertArrayEquals(bubbleSorted, mergeSorted);
    }

    @Property
    <T> void differentialSortingMethodObject(@ForAll("randomObjectArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[] bubbleSorted = Sorting.bubblesort(array.clone(), comparator);
        T[] quickSorted = Sorting.quicksort(array.clone(), comparator);
        T[] mergeSorted = Sorting.mergesort(array.clone(), comparator);

        assertArrayEquals(bubbleSorted, quickSorted);
        assertArrayEquals(bubbleSorted, mergeSorted);
    }



    @Property
    <T> void sortingOrderTestInteger(@ForAll("randomIntegerArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[] sortedMerge = Sorting.mergesort(array.clone(), comparator);
        T[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        T[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);

        for (int i = 1; i < array.length; i++) {
            assertTrue(comparator.compare(sortedMerge[i - 1], sortedMerge[i]) <= 0);
            assertTrue(comparator.compare(sortedBubble[i - 1], sortedBubble[i]) <= 0);
            assertTrue(comparator.compare(sortedQuick[i - 1], sortedQuick[i]) <= 0);
        }
    }

    @Property
    <T> void sortingOrderTestString(@ForAll("randomStringArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[] sortedMerge = Sorting.mergesort(array.clone(), comparator);
        T[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        T[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);

        for (int i = 1; i < array.length; i++) {
            assertTrue(comparator.compare(sortedMerge[i - 1], sortedMerge[i]) <= 0);
            assertTrue(comparator.compare(sortedBubble[i - 1], sortedBubble[i]) <= 0);
            assertTrue(comparator.compare(sortedQuick[i - 1], sortedQuick[i]) <= 0);
        }
    }

    @Property
    <T> void sortingOrderTestObject(@ForAll("randomObjectArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();
        T[] sortedMerge = Sorting.mergesort(array.clone(), comparator);
        T[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        T[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);

        for (int i = 1; i < array.length; i++) {
            assertTrue(comparator.compare(sortedMerge[i - 1], sortedMerge[i]) <= 0);
            assertTrue(comparator.compare(sortedBubble[i - 1], sortedBubble[i]) <= 0);
            assertTrue(comparator.compare(sortedQuick[i - 1], sortedQuick[i]) <= 0);
        }
    }

    @Property
    <T> void idempotenceTestInteger(@ForAll("randomIntegerArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();

        T[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);
        T[] sortedBubbleAgain = Sorting.bubblesort(sortedBubble.clone(), comparator);

        T[] sortedMerge = Sorting.mergesort(array.clone(), comparator);
        T[] sortedMergeAgain = Sorting.mergesort(sortedMerge.clone(), comparator);

        T[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        T[] sortedQuickAgain = Sorting.quicksort(sortedQuick.clone(), comparator);

        assertArrayEquals(sortedBubble, sortedBubbleAgain);
        assertArrayEquals(sortedMerge, sortedMergeAgain);
        assertArrayEquals(sortedQuick, sortedQuickAgain);
    }

    @Property
    <T> void idempotenceTestString(@ForAll("randomStringArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();

        T[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);
        T[] sortedBubbleAgain = Sorting.bubblesort(sortedBubble.clone(), comparator);

        T[] sortedMerge = Sorting.mergesort(array.clone(), comparator);
        T[] sortedMergeAgain = Sorting.mergesort(sortedMerge.clone(), comparator);

        T[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        T[] sortedQuickAgain = Sorting.quicksort(sortedQuick.clone(), comparator);

        assertArrayEquals(sortedBubble, sortedBubbleAgain);
        assertArrayEquals(sortedMerge, sortedMergeAgain);
        assertArrayEquals(sortedQuick, sortedQuickAgain);
    }

    @Property
    <T> void idempotenceTestObject(@ForAll("randomObjectArray") T[] array) {
        Comparator<T> comparator = (Comparator<T>) Comparator.naturalOrder();

        T[] sortedBubble = Sorting.bubblesort(array.clone(), comparator);
        T[] sortedBubbleAgain = Sorting.bubblesort(sortedBubble.clone(), comparator);

        T[] sortedMerge = Sorting.mergesort(array.clone(), comparator);
        T[] sortedMergeAgain = Sorting.mergesort(sortedMerge.clone(), comparator);

        T[] sortedQuick = Sorting.quicksort(array.clone(), comparator);
        T[] sortedQuickAgain = Sorting.quicksort(sortedQuick.clone(), comparator);

        assertArrayEquals(sortedBubble, sortedBubbleAgain);
        assertArrayEquals(sortedMerge, sortedMergeAgain);
        assertArrayEquals(sortedQuick, sortedQuickAgain);
    }

    static class TestObject implements Comparable<TestObject> {
        private final int value;

        public TestObject(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public int compareTo(TestObject other) {
            return Integer.compare(this.value, other.value);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            TestObject that = (TestObject) obj;
            return value == that.value;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return "TestObject{" + "value=" + value + '}';
        }
    }


}
