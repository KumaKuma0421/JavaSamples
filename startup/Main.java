/**
 * Console
 */
package startup;

/**
 * Main
 * @author user01
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//test1();
		//test2();
		//test3();
		test4();
	}
	
	static void test1() {
		var test = new StreamAPI.Sample02();
		var startTick = System.currentTimeMillis();
		test.sample1();
		var endTick = System.currentTimeMillis();
		System.out.println("Elapsed:"+(endTick - startTick));
	}
	
	static void test2() {
		var test = new StreamAPI.Sample02();
		var startTick = System.currentTimeMillis();
		test.sample2();
		var endTick = System.currentTimeMillis();
		System.out.println("Elapsed:"+(endTick - startTick));
	}
	
	static void test3() {
		var test = new Optional.Sample01();
		test.test1();
	}
	
	static void test4() {
		var test = new DateAndTimeAPI.Sample01();
		test.sample1();
	}
}
