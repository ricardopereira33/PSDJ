package calendar.Modes;

import calendar.Interfaces.Interval;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class IntervalMode implements Interval{

    @Override
    public Duration getIntervalDateTime(Temporal d1, Temporal d2){
        return Duration.between(d1, d2);
    }
    
    @Override
    public long getIntervalTimeUnit(Temporal t1, Temporal t2, ChronoUnit unit){
        LocalDateTime time1 = LocalDateTime.from(t1);
        LocalDateTime time2 = LocalDateTime.from(t2);
        
        return unit.between(time2, time1);
    }
   
    /*intervalo pode ser semestral, trimestral...*/
    @Override
    public ArrayList<Temporal> getDates(Temporal start, Duration interval, int numDates){
        ArrayList<Temporal> dates = new ArrayList<>();
        Temporal date = start.plus(interval);
        dates.add(date);
        for(int i=1;i<numDates;i++){
            date = date.plus(interval);
            dates.add(date);
        }
        return dates;
    }
    
}  
