package StreamAPI;

import java.util.stream.IntStream;

public class Sample02 {
    double response;

    // シーケンシャルに処理を行う
    public void sample1() {
        var numbers = IntStream.rangeClosed(1, Integer.MAX_VALUE);
        numbers.filter((i) -> {
            return i % 3 == 0;
        }).forEach((i) -> {
            response = i + i / 2 + i / 3 + i / 4 + i / 5;
        });
    }

    // パラレルに処理を行う
    public void sample2() {
        var numbers = IntStream.rangeClosed(1, Integer.MAX_VALUE);
        numbers.parallel().filter((i) -> {
            return i % 3 == 0;
        }).forEach((i) -> {
            response = i + i / 2 + i / 3 + i / 4 + i / 5;
        });
    }

    // 数値の合計を取得する
    public void sample3() {
        var numbers = IntStream.rangeClosed(1, 100);
        numbers.filter((i) -> {
            return i % 3 == 0;
        }).sum();
    }
}
