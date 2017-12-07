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

//    @Override
//    public Duration getIntervalDateTime(Temporal d1, Temporal d2){
//        return Duration.between(d1, d2);
//    }
    
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
    public long converterUnit(ChronoUnit in, ChronoUnit out, long value){
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime time2= time.plus(value,in);

        return out.between(time,time2);
    }
   
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

    @Override
    public int getNumDayOfWeek(DayOfWeek day, Temporal start, Temporal end){
        long numDays = ChronoUnit.DAYS.between(start,end);
        numDays = numDays - Math.abs(start.get(ChronoField.DAY_OF_WEEK) - day.getValue());
        float numDayOfWeek = numDays/7;
        return (int) numDayOfWeek;
    }

}  
