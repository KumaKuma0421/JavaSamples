package Optional;

import java.util.HashMap;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.stream.IntStream;

public class Sample01 {
    HashMap<Integer, String> numStrings = new HashMap<>();

    private void generateData() {
        var numbers = IntStream.rangeClosed(1, 100);
        numbers.forEach((i) -> {
            if (i % 10 == 0) {
                numStrings.put(i, null);
            } else {
                numStrings.put(i, "This is No." + i);
            }
        });
    }

    // nullの可能性のある結果をOptionalで包みます。
    // nullの返却の可能性を利用者に伝えます。
    private Optional<String> find(Integer index) {
        var response = Optional.ofNullable(numStrings.get(index));
        return response;
    }

    public void test1() {
        this.generateData();
        BiConsumer<Integer, String> action = (i, s) -> {
            // 利用者はOptionalを介してnullの存在の対処を行います。
            var response = this.find(i).orElse("Default");
            System.out.println(response);
        };
        numStrings.forEach(action);
    }
}
