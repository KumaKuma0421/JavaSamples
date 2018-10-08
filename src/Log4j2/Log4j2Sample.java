package Log4j2;

import java.util.stream.IntStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j2Sample {
    Logger myLogger = LogManager.getLogger(this.getClass());

    public void Test01() {
        this.myLogger.trace("{}", "arg1");
        this.myLogger.debug("{}", "arg1");
        this.myLogger.info("{}", "arg1");
        this.myLogger.warn("{} {}", "arg1", "arg2");
        this.myLogger.error("{}", "arg1");
    }

    public void Test02() {
        var loop = IntStream.rangeClosed(1, 10000);
        loop.forEach((i) -> {
            this.myLogger.info("count {}", i);
            this.Test01();
        });
    }

}
