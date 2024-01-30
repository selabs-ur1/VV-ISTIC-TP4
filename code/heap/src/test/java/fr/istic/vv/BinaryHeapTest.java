package fr.istic.vv;
import net.jqwik.api.*;
import java.util.Comparator;

public class BinaryHeapTest {
    @Property
    boolean absoluteValueOfAllNumbersIsPositive(@ForAll int anInteger) {
        return Math.abs(anInteger) >= 0;
    }

    @Property
    boolean popReturnsMinimum(
            @ForAll("randomHeapInputs") HeapInput<Integer> heapInput
    ) {
        BinaryHeap<Integer> binaryHeap = new BinaryHeap<Integer>(Comparator.naturalOrder());

        for (Integer element : heapInput.getElements()) {
            binaryHeap.push(element);
        }

        Integer[] sortedElements = heapInput.getElements().stream()
                .sorted(Comparator.naturalOrder())
                .toArray(Integer[]::new);

        for (int i = 0; i < sortedElements.length; i++) {
            Integer minElement = binaryHeap.pop();
            if (!minElement.equals(sortedElements[i])) {
                return false;
            }
        }

        return true;
    }

    @Provide
    Arbitrary<HeapInput<Integer>> randomHeapInputs() {
        return Arbitraries.integers().list().ofMinSize(0).map(HeapInput::new);
    }

    static class HeapInput<T> {
        private final java.util.List<T> elements;

        HeapInput(java.util.List<T> elements) {
            this.elements = elements;
        }

        java.util.List<T> getElements() {
            return elements;
        }
    }
}
