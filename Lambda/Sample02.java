package Lambda;

import java.util.*;

public class Sample02 {
	public Integer[] numbers = { -1, 2, 0, -3, 8 };
	
	Comparator<Integer> ascending = (a, b) -> {return a-b;};
	
	public void sortSample () {
		List<Integer> numbersList = Arrays.asList(numbers);
		Collections.sort(numbersList, ascending);
	}
}
