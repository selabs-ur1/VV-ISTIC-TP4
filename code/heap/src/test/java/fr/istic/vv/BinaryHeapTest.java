package fr.istic.vv;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryHeapTest {

    @Property
    boolean testPushAndPopMaintainsOrder(@ForAll @Size(min = 1, max = 100) List<Integer> inputElements) {
        Comparator<Integer> elementComparator = Integer::compareTo;
        BinaryHeap<Integer> testHeap = new BinaryHeap<>(elementComparator);

        inputElements.forEach(testHeap::push);

        List<Integer> expectedSortedElements = inputElements.stream().sorted(elementComparator).toList();

        for (Integer expectedValue : expectedSortedElements) {
            Integer actualValue = testHeap.pop();
            if (!expectedValue.equals(actualValue)) {
                return false;
            }
        }

        return true;
    }

    @Property
    boolean testPeekReturnsSmallestElement(@ForAll @Size(min = 1, max = 100) List<Integer> inputElements) {
        Comparator<Integer> elementComparator = Integer::compareTo;
        BinaryHeap<Integer> testHeap = new BinaryHeap<>(elementComparator);

        inputElements.forEach(testHeap::push);

        List<Integer> expectedSortedElements = inputElements.stream().sorted(elementComparator).toList();

        for (Integer expectedValue : expectedSortedElements) {
            Integer actualValue = testHeap.peek();
            if (!expectedValue.equals(actualValue)) {
                return false;
            }
            testHeap.pop();
        }

        return true;
    }

    @Property
    boolean testCountReflectsHeapSize(@ForAll @Size(min = 0, max = 100) List<Integer> inputElements) {
        Comparator<Integer> elementComparator = Integer::compareTo;
        BinaryHeap<Integer> testHeap = new BinaryHeap<>(elementComparator);

        inputElements.forEach(testHeap::push);

        return testHeap.count() == inputElements.size();
    }

    @Property
    boolean testPopThrowsOnEmptyHeap() {
        Comparator<Integer> elementComparator = Integer::compareTo;
        BinaryHeap<Integer> testHeap = new BinaryHeap<>(elementComparator);

        try {
            testHeap.pop();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Property
    boolean testPeekThrowsOnEmptyHeap() {
        Comparator<Integer> elementComparator = Integer::compareTo;
        BinaryHeap<Integer> testHeap = new BinaryHeap<>(elementComparator);

        try {
            testHeap.peek();
            return false;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Property
    boolean testPushHandlesDuplicateElements(@ForAll @Size(min = 1, max = 100) List<Integer> inputElements) {
        Comparator<Integer> elementComparator = Integer::compareTo;
        BinaryHeap<Integer> testHeap = new BinaryHeap<>(elementComparator);

        inputElements.forEach(testHeap::push);

        List<Integer> expectedSortedElements = inputElements.stream().sorted(elementComparator).toList();

        for (Integer expectedValue : expectedSortedElements) {
            Integer actualValue = testHeap.pop();
            if (!expectedValue.equals(actualValue)) {
                return false;
            }
        }

        return true;
    }

    @Property
    boolean testHeapWithDifferentComparators(@ForAll @Size(min = 1, max = 100) List<Integer> inputElements) {
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        BinaryHeap<Integer> testHeap = new BinaryHeap<>(reverseComparator);

        inputElements.forEach(testHeap::push);

        List<Integer> expectedSortedElements = inputElements.stream().sorted(reverseComparator).toList();

        for (Integer expectedValue : expectedSortedElements) {
            Integer actualValue = testHeap.pop();
            if (!expectedValue.equals(actualValue)) {
                return false;
            }
        }

        return true;
    }
}