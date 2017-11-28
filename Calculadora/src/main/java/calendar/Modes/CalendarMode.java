package calendar.Modes;

import calendar.Interfaces.Calendar;
import java.time.*;
import java.time.temporal.*;

public class CalendarMode implements Calendar {

    @Override
    public DayOfWeek getFistDayOfTheYear(Year y){
        LocalDate ld = LocalDate.ofYearDay(y.getValue(), 1);
        return ld.getDayOfWeek();
    }

    @Override
    public DayOfWeek getLastDayOfTheYear(Year y){
        LocalDate ld = LocalDate.of(y.getValue(), 12,31);
        return ld.getDayOfWeek();
    }

    @Override
    public DayOfWeek getDayOfTheWeek(TemporalAccessor temporal){
        LocalDate date;
        try{
            date = LocalDate.from(temporal);
        }
        catch(DateTimeException e){
            return null;
        }
        return date.getDayOfWeek();
    }

    @Override
    public Month getMonthOfYear(int dayOfYear, Year y){
        LocalDate ld = LocalDate.ofYearDay(y.getValue(),dayOfYear);
        return ld.getMonth();
    }

    public Duration getTimeTillEndYear(){
        return Duration.between(LocalDateTime.now(),LocalDateTime.of(2017,12,31,23,0,0,0));
    }

    public int getNumDayOfWeek(DayOfWeek day, Temporal start, Temporal end){
        long numDays = ChronoUnit.DAYS.between(start,end);
        numDays = numDays - Math.abs(start.get(ChronoField.DAY_OF_WEEK) - day.getValue());
        float numDayOfWeek = numDays/7;
        return (int)numDayOfWeek;
    }
}
