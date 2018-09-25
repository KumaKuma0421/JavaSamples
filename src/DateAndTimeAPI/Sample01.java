package DateAndTimeAPI;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;

public class Sample01 {
    public void getLocalDateTime() {
        var localTime = LocalDateTime.now().toString();
        System.out.println("LocalTime:" + localTime);
    }

    public void getOffsetDateTime() {
        var offsetTime = OffsetDateTime.now().toString();
        System.out.println("OffsetTime:" + offsetTime);
    }

    public void getZonedDateTime() {
        var zonedTime = ZonedDateTime.now().toString();
        System.out.println("ZonedTime:" + zonedTime);
    }
}
