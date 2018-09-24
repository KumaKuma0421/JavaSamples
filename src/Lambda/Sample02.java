package Lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Sample02 {
    public Integer[] numbers = { -1, 2, 0, -3, 8 };

    Comparator<Integer> ascending = (a, b) -> {
        return a - b;
    };

    // リストのソートにLambdaを使用する
    public void sortSample() {
        List<Integer> numbersList = Arrays.asList(numbers);
        Collections.sort(numbersList, ascending);
    }
}
