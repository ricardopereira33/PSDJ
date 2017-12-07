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
    public long getIntervalTimeUnit(Temporal t1, Temporal t2, ChronoUnit unit){
        LocalDateTime time1 = LocalDateTime.from(t1);
        LocalDateTime time2 = LocalDateTime.from(t2);
        
        return unit.between(time2, time1);
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
    public long numWorkingDays(Temporal t1, Temporal t2) {
        LocalDate time1 = LocalDate.from(t1);
        LocalDate time2 = LocalDate.from(t2);
        int conta = 0;
        
        while(time1.isBefore(time2)){
            DayOfWeek dia = time1.getDayOfWeek();
            if(! (dia.equals(SATURDAY) || dia.equals(SUNDAY)))  conta++; 
            time1 = time1.plus(1, ChronoUnit.DAYS);
        }
        
        return conta;
    }

    @Override
    public long numNonWorkingDays(Temporal t1, Temporal t2){
        LocalDate time1 = LocalDate.from(t1);
        LocalDate time2 = LocalDate.from(t2);

        long workingdays = numWorkingDays(t1,t2);
        return ChronoUnit.DAYS.between(t1,t2) - workingdays;
    }

    @Override
    public int getNumDayOfWeek(DayOfWeek day, Temporal start, Temporal end){
        long numDays = ChronoUnit.DAYS.between(start,end);
        numDays = numDays - Math.abs(start.get(ChronoField.DAY_OF_WEEK) - day.getValue());
        float numDayOfWeek = numDays/7;
        return (int) numDayOfWeek;
    }

    @Override
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone) {
        ZoneId start_zone_id = ZoneId.of(startZone);
        ZonedDateTime start_date_time = start.atZone(start_zone_id);
        ZoneId end_zone_id = ZoneId.of(endZone);
        ZonedDateTime end_date_time = end.atZone(end_zone_id);
        Duration duration = Duration.between(start_date_time, end_date_time);
        return duration;
    }
}  
