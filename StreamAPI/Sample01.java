package StreamAPI;

import java.util.*;
import java.util.function.*;

public class Sample01 {
	Integer[] numbers = { -1, 2, 0, -3, 8 };
	List<Integer> numbersList = Arrays.asList(numbers);
	
	Consumer<Integer> action = (i)->{ System.out.println(i); };
	Predicate<Integer> filter = (i)->{ return i > 0; };
	Function<Integer, Integer> square = (i)->{ return i * i;};
	Comparator<Integer> sorter = (i, j)->{ return i-j; };
	
	public void sampleForEach() {
		numbersList.stream().forEach(action);
	}
	
	public void sampleFilter() {
		numbersList.stream().filter(filter);
	}
	
	public void sampleMap() {
		numbersList.stream().map(square);
	}
	
	public void sampleSort() {
		numbersList.stream().sorted(sorter);
	}
	
	public void sample1() {
		numbersList.stream()
		.filter(filter)
		.map(square)
		.sorted(sorter)
		.forEach(action);
	}
}
