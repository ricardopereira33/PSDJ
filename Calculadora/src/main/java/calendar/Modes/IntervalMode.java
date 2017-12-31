package calendar.Modes;

import calendar.Interfaces.Interval;

import java.time.*;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;

import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class IntervalMode implements Interval{
    
    public IntervalMode(){
    }
    
    @Override
    public LocalDateTime addDateTime(LocalDateTime date_time, ChronoUnit unit, int value) {
        LocalDateTime result = unit.addTo(date_time, value);
        return result;
    }

    @Override
    public LocalDateTime subDateTime(LocalDateTime date_time, ChronoUnit unit, int value) {
        LocalDateTime result = unit.addTo(date_time, -value);
        return result;
    }
    
    @Override
    public long getIntervalTimeUnit(Temporal t1, Temporal t2, ChronoUnit unit){
        LocalDateTime time1 = LocalDateTime.from(t1);
        LocalDateTime time2 = LocalDateTime.from(t2);
        
        return unit.between(time1, time2);
    }
    
    @Override
    public double converterUnit(ChronoUnit in, ChronoUnit out, long value){
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime time2= time.plus(value,in);

        return between(time, time2, out);
    }
    
    public static double between(Temporal startInclusive, Temporal endExclusive, ChronoUnit unit) {
        Duration duration = Duration.between(startInclusive, endExclusive);
        long conversion = Duration.of(1, unit).toNanos();
    return (double) duration.toNanos() / conversion;
}
}  
