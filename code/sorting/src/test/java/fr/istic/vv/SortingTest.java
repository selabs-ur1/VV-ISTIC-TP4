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
        return Arbitraries.integers().array(Integer[].class).ofMinSize(0).ofMaxSize(100);
    }

    @Provide
    Arbitrary<String[]> randomStringArray() {
        return Arbitraries.strings().withCharRange('a', 'z').array(String[].class).ofMinSize(0).ofMaxSize(50);
    }

    @Provide
    Arbitrary<TestObject[]> randomObjectArray() {
        return Arbitraries.integers().array(Integer[].class).map(array -> Arrays.stream(array).map(TestObject::new).toArray(TestObject[]::new));
    }

    @Property
    <T> void differentialFuzzing(@ForAll("randomIntegerArray") T[] array, @ForAll Comparator<T> comparator) {
        T[] bubbleSorted = Sorting.bubblesort(array.clone(), comparator);
        T[] quickSorted = Sorting.quicksort(array.clone(), comparator);
        T[] mergeSorted = Sorting.mergesort(array.clone(), comparator);

        assertArrayEquals(bubbleSorted, quickSorted, "BubbleSort and QuickSort results differ");
        assertArrayEquals(bubbleSorted, mergeSorted, "BubbleSort and MergeSort results differ");
    }

    @Property
    <T> void sortingOrderTest(@ForAll("randomStringArray") T[] array, @ForAll Comparator<T> comparator) {
        T[] sorted = Sorting.mergesort(array.clone(), comparator);

        for (int i = 1; i < sorted.length; i++) {
            assertTrue(comparator.compare(sorted[i - 1], sorted[i]) <= 0, "Array is not sorted");
        }
    }

    @Property
    void idempotenceTest(@ForAll("randomObjectArray") TestObject[] array) {
        Comparator<TestObject> comparator = Comparator.comparingInt(TestObject::getValue);
        TestObject[] sorted = Sorting.bubblesort(array.clone(), comparator);
        TestObject[] sortedAgain = Sorting.bubblesort(sorted.clone(), comparator);

        assertArrayEquals(sorted, sortedAgain, "Sorting twice should yield the same result");
    }

    static class TestObject {
        private final int value;

        public TestObject(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
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
