package calendar.Modes;

import calendar.Interfaces.Calendar;
import calendar.Util.Util_Datas;

import java.time.*;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;
import calendar.Interfaces.TimeZone;

public class CalendarMode implements Calendar {

    public CalendarMode(){
        
    }
    
    @Override
    public int numWorkingDays(Temporal t1, Temporal t2) {
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
    public int numNonWorkingDays(Temporal t1, Temporal t2){
        LocalDate time1 = LocalDate.from(t1);
        LocalDate time2 = LocalDate.from(t2);

        long workingdays = numWorkingDays(t1,t2);
        return (int) (ChronoUnit.DAYS.between(t1,t2) - workingdays);
    }
    
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

    @Override
    public Duration timeSince(ChronoUnit unit){
        if(unit == ChronoUnit.DAYS){
            LocalDateTime l = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.WEEKS){

            int day = LocalDateTime.now().getDayOfWeek().getValue();
            LocalDateTime l = LocalDateTime.now().minusDays(day-1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.MONTHS){
            LocalDateTime l = LocalDateTime.now().withDayOfMonth(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.YEARS){
            LocalDateTime l = LocalDateTime.now().withDayOfYear(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.DECADES){
            LocalDateTime l = LocalDateTime.of(2010,1,1,0,0,0,0);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.CENTURIES){
            LocalDateTime l = LocalDateTime.of(2000,1,1,0,0,0,0);
            return Duration.between(l,LocalDateTime.now());
        }
        else{
            //ERA da informacao ??
            LocalDateTime l = LocalDateTime.of(1981,1,1,0,0,0,0);
            return Duration.between(l,LocalDateTime.now());
        }
    }

    @Override
    public Duration timeUntil(ChronoUnit unit){
        if(unit == ChronoUnit.DAYS){
            LocalDateTime l = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59).withNano(999999);
            return Duration.between(LocalDateTime.now(),l);
        }
        else if(unit == ChronoUnit.WEEKS){
            int day = LocalDateTime.now().getDayOfWeek().getValue();
            LocalDateTime l = LocalDateTime.now().plusDays(7-day).withHour(59).withMinute(59).withSecond(59).withNano(999999);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.MONTHS){
            LocalDateTime l = LocalDateTime.now().withDayOfMonth(31).withHour(59).withMinute(59).withSecond(59).withNano(999999);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.YEARS){
            LocalDateTime l = LocalDateTime.now().withDayOfYear(365).withHour(59).withMinute(59).withSecond(59).withNano(999999);
            return Duration.between(LocalDateTime.now(),l);
        }
        else if(unit == ChronoUnit.DECADES){
            LocalDateTime l = LocalDateTime.of(2020,1,1,0,0,0,0);
            return Duration.between(LocalDateTime.now(),l);
        }
        else {//centuries
            LocalDateTime l = LocalDateTime.of(2100,1,1,0,0,0,0);
            return Duration.between(LocalDateTime.now(),l);
        }
    }

    public LocalDate firstDayInfo(ChronoUnit unit, LocalDate date){
        if(unit == ChronoUnit.WEEKS){
            int day = date.getDayOfWeek().getValue();
            LocalDate l = date.minusDays(day-1);
            return l;
        }
        else if(unit == ChronoUnit.MONTHS){
            LocalDate l = date.withDayOfMonth(1);
            return l;
        }
        else if(unit == ChronoUnit.YEARS){
            LocalDate l = date.withDayOfYear(1);
            return l;
        }
        else if(unit == ChronoUnit.DECADES){
            int year = date.getYear();
            int r = year % 10;
            LocalDate l = LocalDate.of(year-r,1,1);
            return  l;
        }
        else{//centuries
            int year = date.getYear();
            int r = year % 100;
            LocalDate l = LocalDate.of(year-r,1,1);
            return l;
        }
    }

    public LocalDate lastDayInfo(ChronoUnit unit, LocalDate date){
        if(unit == ChronoUnit.WEEKS){
            int day = date.getDayOfWeek().getValue();
            LocalDate l = date.plusDays(7-day);
            return l;
        }
        else if(unit == ChronoUnit.MONTHS){
            LocalDate l = date.withDayOfMonth(31);
            return l;
        }
        else if(unit == ChronoUnit.YEARS){
            LocalDate l = date.withDayOfYear(365);
            return l;
        }
        else if(unit == ChronoUnit.DECADES){
            int year = date.getYear();
            int r = year % 10;
            LocalDate l = LocalDate.of(year+(10-r),1,1);
            return  l;
        }
        else{//centuries
            int year = date.getYear();
            int r = year % 100;
            LocalDate l = LocalDate.of(year+(100-r),1,1);
            return l;
        }
    }
}
