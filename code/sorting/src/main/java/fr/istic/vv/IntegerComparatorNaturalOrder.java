package fr.istic.vv;

import java.util.Comparator;

public class IntegerComparatorNaturalOrder implements Comparator<Integer>{

	@Override
	public int compare(Integer x1, Integer x2) {
		if(x1 < x2)
			return -1;
		else if(x1 > x2)
			return 1;
		else 
			return 0;
	}

}
