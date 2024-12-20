package fr.istic.vv;
import net.jqwik.api.*;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;


public class BinaryHeapTest {

    @Provide
    Arbitrary<Comparator<Integer>> integerComparators(){
        return Arbitraries.of(Comparator.naturalOrder(), Comparator.reverseOrder());
    }

    @Provide
    Arbitrary<Comparator<String>> stringComparators(){
        return Arbitraries.of(Comparator.naturalOrder(), Comparator.reverseOrder());
    }

    @Provide
    Arbitrary<List<Integer>> integerLists(){
        Arbitrary<Integer> ints = Arbitraries.integers();
        return ints.list();
    }

    @Provide
    Arbitrary<List<String>> stringLists(){
        Arbitrary<String> strings = Arbitraries.strings();
        return strings.list();
    }

    private <T> void fillHeap(BinaryHeap<T> heap, List<T> values){
        for(T value : values){
            heap.push(value);
        }
    }

    @Property
    boolean increaseOnPushInt(@ForAll @From("integerLists") List<Integer> values,
                              @ForAll @From("integerComparators") Comparator<Integer> comparator){
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        fillHeap(heap, values);
        return heap.count() == values.size();
    }

    @Property
    boolean decreaseOnPopInt(@ForAll @From("integerLists") List<Integer> values,
                              @ForAll @From("integerComparators") Comparator<Integer> comparator){
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        Assume.that(!values.isEmpty());
        fillHeap(heap, values);
        int size = values.size();
        int i = 0;
        while(i < size){
            heap.pop();
            i++;
            if(heap.count() != size - i) return false;
        }
        return heap.count() == 0;
    }

    @Property
    boolean noDecreaseOnPeekInt(@ForAll @From("integerLists") List<Integer> values,
                             @ForAll @From("integerComparators") Comparator<Integer> comparator){
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        Assume.that(!values.isEmpty());
        fillHeap(heap, values);
        heap.peek();
        return heap.count() == values.size();
    }

    @Property
    boolean popAlwaysMinInteger(@ForAll @From("integerLists") List<Integer> values,
                                @ForAll @From("integerComparators") Comparator<Integer> comparator){
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        Assume.that(!values.isEmpty());
        fillHeap(heap, values);
        values.sort(comparator);
        return heap.pop().equals(values.get(0));
    }

    @Property
    boolean popIsSorted(@ForAll @From("integerLists") List<Integer> values,
                                @ForAll @From("integerComparators") Comparator<Integer> comparator){
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        Assume.that(!values.isEmpty());
        fillHeap(heap, values);
        values.sort(comparator);
        for(Integer value : values){
            if(!Objects.equals(value, heap.pop())) return false;
        }
        return true;
    }

    @Property
    boolean popAlwaysMinString(@ForAll @From("stringLists") List<String> values,
                                @ForAll @From("stringComparators") Comparator<String> comparator){
        BinaryHeap<String> heap = new BinaryHeap<>(comparator);
        Assume.that(!values.isEmpty());
        fillHeap(heap, values);
        values.sort(comparator);
        return heap.pop().equals(values.get(0));
    }

    @Property
    boolean peekAlwaysMinInteger(@ForAll @From("integerLists") List<Integer> values,
                                @ForAll @From("integerComparators") Comparator<Integer> comparator){
        BinaryHeap<Integer> heap = new BinaryHeap<>(comparator);
        Assume.that(!values.isEmpty());
        fillHeap(heap, values);
        values.sort(comparator);
        return heap.peek().equals(values.get(0));
    }

    @Property
    boolean peekAlwaysMinString(@ForAll @From("stringLists") List<String> values,
                               @ForAll @From("stringComparators") Comparator<String> comparator){
        BinaryHeap<String> heap = new BinaryHeap<>(comparator);
        Assume.that(!values.isEmpty());
        fillHeap(heap, values);
        values.sort(comparator);
        return heap.peek().equals(values.get(0));
    }


}
