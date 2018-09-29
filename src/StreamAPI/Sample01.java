package StreamAPI;

import java.util.Comparator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Sample01 {

    public Consumer<Integer> action = (i) -> {
        System.out.println(i);
    };

    public Predicate<Integer> filter = (i) -> {
        return i > 0;
    };

    public Function<Integer, Integer> square = (i) -> {
        return i * i;
    };

    public Comparator<Integer> sorter = (i, j) -> {
        return i - j;
    };
}
