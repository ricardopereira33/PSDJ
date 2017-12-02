package calendar.Modes;

import calendar.Interfaces.Calendar;
import calendar.Interfaces.Converter;
import calendar.Util.Util_Datas;

import java.time.*;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public long getTimeTillEndYear(){
        TemporalAdjuster end = TemporalAdjusters.lastDayOfYear();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateEnd = now.with(end);
           
        return Duration.between(now,dateEnd).toDays();
    }

    @Override
    public long getTimePassedStartYear() {
        TemporalAdjuster start = TemporalAdjusters.firstDayOfYear();
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime dateStart = now.with(start);
        
        return Duration.between(dateStart, now).toDays();
    }

    @Override
    public List<Integer> getLeapYear(ChronoUnit unit, int value) {
        ArrayList<Integer> list = new ArrayList<>();

        if(!ChronoUnit.CENTURIES.equals(unit))
            return null;

        int year = (value-1)*100;
        for(int i = 1; i<=100; i++){
            LocalDate date = LocalDate.ofYearDay(year,1);
            if(date.isLeapYear())
                list.add(year);
            year += 1;
        }
        return list;
    }
    
}
