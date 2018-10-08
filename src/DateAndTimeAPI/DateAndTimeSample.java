package DateAndTimeAPI;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

public class DateAndTimeSample {

    public Duration getDuration(LocalDateTime base, LocalDateTime current) {
        var duration = Duration.between(base, current);
        return duration;
    }

    public Period getPeriod(LocalDateTime base, LocalDateTime current) {
        var period = Period.between(base.toLocalDate(), current.toLocalDate());
        return period;
    }
}
