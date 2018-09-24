package startup;

public class Main {

    public static void main(String[] args) {
        // test1();
        // test2();
        // test3();
        // test4();
        // test5();
        test6();
    }

    static void test1() {
        var test = new StreamAPI.Sample02();
        var startTick = System.currentTimeMillis();
        test.sample1();
        var endTick = System.currentTimeMillis();
        System.out.println("Elapsed:" + (endTick - startTick));
    }

    static void test2() {
        var test = new StreamAPI.Sample02();
        var startTick = System.currentTimeMillis();
        test.sample2();
        var endTick = System.currentTimeMillis();
        System.out.println("Elapsed:" + (endTick - startTick));
    }

    static void test3() {
        var test = new Optional.Sample01();
        test.test1();
    }

    static void test4() {
        var test = new DateAndTimeAPI.Sample01();
        test.sample1();
    }

    static void test5() {
        var test = new File.Sample01();
        var fileName = "/home/user01/SampleText";
        test.lineWriter(fileName);
        test.lineReader(fileName);
    }

    static void test6() {
        var test = new Execute.Sample01();
        test.processSample(new String[] { "java", "-version" });
        test.processSample(new String[] { "vmstat", "1", "10" });
    }
}
