package calendar.Modes;

import calendar.Interfaces.Calendar;

import java.time.*;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import java.time.temporal.*;
import java.util.ArrayList;
import java.util.List;
import calendar.Interfaces.TimeZone;
import calendar.Util.WeekendQuery;

public class CalendarMode implements Calendar {

    public CalendarMode(){
        
    }
    
    @Override
    public int numOfDaysOfWeek(Temporal t1, Temporal t2, DayOfWeek day) {
        LocalDate time1 = LocalDate.from(t1);
        LocalDate time2 = LocalDate.from(t2);
        int numDaysOfWeek = 0;
        
        while(time1.isBefore(time2)){
            DayOfWeek time1Day = time1.getDayOfWeek();
            if(time1Day.equals(day)) numDaysOfWeek++;
            time1 = time1.plus(1, ChronoUnit.DAYS);
        }
        if(time2.getDayOfWeek().equals(day)) numDaysOfWeek++;
        
        return numDaysOfWeek;
    }
    
    @Override
    public int numWorkingDays(Temporal t1, Temporal t2) {
        int nonWorkingDays = numNonWorkingDays(t1, t2);
        return (int) ChronoUnit.DAYS.between(t1, t2) + 1 - nonWorkingDays;
    }

    @Override
    public int numNonWorkingDays(Temporal t1, Temporal t2){
        int saturdays = numOfDaysOfWeek(t1, t2, DayOfWeek.SATURDAY);
        int sundays = numOfDaysOfWeek(t1, t2, DayOfWeek.SUNDAY);
        return saturdays + sundays;
    }

    public int numWeekends(Temporal t1, Temporal t2){
        LocalDate time1 = LocalDate.from(t1);
        LocalDate time2 = LocalDate.from(t2);
        int weekends = 0;

        while(time1.isBefore(time2)){
            if(time1.query(new WeekendQuery())) {
                time1 = time1.plus(1, ChronoUnit.DAYS);
                weekends++;
            }
            time1 = time1.plus(1, ChronoUnit.DAYS);
        }
        return weekends;
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
            LocalDateTime l = LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(LocalDateTime.now(),l);
        }
        else if(unit == ChronoUnit.WEEKS){
            int day = LocalDateTime.now().getDayOfWeek().getValue();
            LocalDateTime l = LocalDateTime.now().plusDays(1).plusDays(7-day).withHour(0).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.MONTHS){
            int maxDayMonth = LocalDateTime.now().getMonth().maxLength();
            LocalDateTime l = LocalDateTime.now().plusDays(1).withDayOfMonth(maxDayMonth).withHour(0).withMinute(0).withSecond(0).withNano(0);
            return Duration.between(l,LocalDateTime.now());
        }
        else if(unit == ChronoUnit.YEARS){
            int maxDayYear = Year.now().length();
            LocalDateTime l = LocalDateTime.now().plusDays(1).withDayOfYear(maxDayYear).withHour(0).withMinute(0).withSecond(0).withNano(0);
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
            int maxDayMonth = date.getMonth().maxLength();
            LocalDate l = date.withDayOfMonth(maxDayMonth);
            return l;
        }
        else if(unit == ChronoUnit.YEARS){
            int maxDayYear = Year.now().length();
            LocalDate l = date.withDayOfYear(maxDayYear);
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

