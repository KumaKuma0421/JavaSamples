package Lambda;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class LambdaSample {

    // 関数型インターフェースの宣言
    @FunctionalInterface
    public interface Out {
        public void show(String message);
    }

    public void show(String message) {
        Out out = (msg) -> {
            System.out.println(msg);
        };
        out.show(message);
    }

    // Supplier<T> T supplier.get();
    public Supplier<String> mySupplier = () -> {
        return "supplied.";
    };

    // Consumer<T> void consumer.accept(T t);
    public Consumer<String> myConsumer = (msg) -> {
        System.out.println(msg);
    };

    // Predicate<T> boolean Predicate.test(T t);
    public Predicate<String> predicate = (msg) -> {
        return msg.equalsIgnoreCase("JAVA");
    };

    // Function<T, R> R function.apply(T t);
    public Function<Integer, String> function = (i) -> {
        return String.format("This is No.%d", i);
    };

    // UnaryOperator<T> T unaryOpeator.apply(T t)
    public UnaryOperator<Integer> unaryOperator = (i) -> {
        return i * i;
    };

    // BinaryOperator<T> T binaryOperator.apply(T left, T right);
    public BinaryOperator<Integer> binaryOperator = (i, j) -> {
        return i * j;
    };

    // Comparator<T> comparator.compare(T left, T right);
    public Comparator<Integer> ascending = (a, b) -> {
        return a - b;
    };
}