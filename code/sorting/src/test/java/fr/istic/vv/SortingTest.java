package fr.istic.vv;
import net.jqwik.api.*;
import org.assertj.core.api.Assertions;

import java.util.Comparator;

public class SortingTest<T>{

    //We can't test on all types, so let's test on Strings and Integers
    //We could also make a type generator but not easy to match with a generated comparator

    @Provide
    public Arbitrary<String[]> stringArrays(){
        Arbitrary<String> a = Arbitraries.strings();
        return a.array(String[].class);
    }

    @Provide
    public Arbitrary<Comparator<String>> stringComparators(){
        return Arbitraries.of(Comparator.naturalOrder(), Comparator.reverseOrder());
    }

    @Provide
    public Arbitrary<Integer[]> integerArrays(){
        Arbitrary<Integer> a = Arbitraries.integers();
        return a.array(Integer[].class);
    }

    @Provide
    public Arbitrary<Comparator<Integer>> integerComparators() {
        return Arbitraries.of(Comparator.naturalOrder(), Comparator.reverseOrder());
    }

    @Property
    public void diffFuzzTestWithIntegers(@ForAll @From("integerArrays") Integer[] input,
                                          @ForAll @From("integerComparators") Comparator<Integer> integerComparator){
        Integer[] bubble = Sorting.bubblesort(input, integerComparator);
        Integer[] quick = Sorting.quicksort(input, integerComparator);
        Integer[] merge = Sorting.mergesort(input, integerComparator);

        Assertions.assertThat(bubble).isEqualTo(quick);
        Assertions.assertThat(quick).isEqualTo(merge);
        Assertions.assertThat(bubble).isSortedAccordingTo(integerComparator);
    }

    @Property
    public void diffFuzzTestWithStrings(@ForAll @From("stringArrays") String[] input,
                                         @ForAll @From("stringComparators") Comparator<String> stringComparator){
        String[] bubble = Sorting.bubblesort(input, stringComparator);
        String[] quick = Sorting.quicksort(input, stringComparator);
        String[] merge = Sorting.mergesort(input, stringComparator);

        Assertions.assertThat(bubble).isEqualTo(quick);
        Assertions.assertThat(quick).isEqualTo(merge);
        Assertions.assertThat(bubble).isSortedAccordingTo(stringComparator);
    }
}