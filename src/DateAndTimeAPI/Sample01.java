package DateAndTimeAPI;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Sample01 {
    public void sample1() {
        var localTime = LocalDateTime.now().toString();
        var offsetTime = OffsetDateTime.now().toString();
        var zonedTime = ZonedDateTime.now().toString();
        System.out.println("LocalTime:" + localTime + " OffsetTime:" + offsetTime + " ZonedTime:" + zonedTime);
    }
}
