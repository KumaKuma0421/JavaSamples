package Lambda;

import java.util.function.*;

/**
 * Lambda Sample
 * JDK1.8以降で使用可能
 */
public class Sample01 {
	
	/**
	 * 関数型インターフェースの宣言
	 */
	@FunctionalInterface
	public interface Out {
		public void show(String message);
	}
	
	/**
	 * 関数型インターフェースの実装と使用
	 */
	public void lambdaTest () {
		Out out = (msg) -> { System.out.println(msg); };
		out.show("Hallo, Lambda");
	}
	
	/**
	 * Supplier<T> T supplier.get();
	 */
	public Supplier<String> supplier = () -> {
		return "supplied.";
	};
	
	public void lambdaSupplier() {
		System.out.println(supplier.get());
	}
	
	/**
	 * Consumer<T> void consumer.accept(T t);
	 */
	public Consumer<String> consumer = (msg) -> {
		System.out.println(msg);
	};
	
	public void lambdaConsumer() {
		consumer.accept("Lambda Consumer.");
	}
	
	/**
	 * Predicate<T> boolean Predicate.test(T t);
	 */
	public Predicate<String> predicate = (msg) -> {
		return msg.equalsIgnoreCase("JAVA");
	};
	
	public void lambdaPredicate() {
		System.out.println(predicate.test("Java"));
	}
	
	/**
	 * Function<T, R> R function.apply(T t);
	 */
	public Function<Integer, String> function = (i) -> {
		return String.format("This is No.%d", i);
	};
	
	public void lambdaFunction() {
		System.out.println(function.apply(120));
	}
	
	/**
	 * UnaryOperator<T> T unaryOpeator.apply(T t)
	 */
	public UnaryOperator<Integer> unaryOperator = (i) -> {
		return i * i;
	};
	
	public void lambdaUnaryOperator() {
		System.out.println(unaryOperator.apply(3));
	}
	
	/**
	 * BinaryOperator<T> T binaryOperator.apply(T left, T right);
	 */
	public BinaryOperator<Integer> binaryOperator = (i, j) -> {
		return i * j;
	};
	
	public void lambdaBinaryOperation() {
		System.out.println(binaryOperator.apply(3, 4));
	}
}
