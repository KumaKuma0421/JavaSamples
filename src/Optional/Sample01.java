package Optional;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.IntStream;

public class Sample01 {
    HashMap<Integer, String> numStrings = new HashMap<>();

    public void generateData() {
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
    public Optional<String> find(Integer index) {
        return Optional.ofNullable(numStrings.get(index));
    }
}
