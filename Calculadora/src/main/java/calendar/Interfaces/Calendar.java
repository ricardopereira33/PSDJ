package calendar.Interfaces;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public interface Calendar {
    
    // número de um determinado dia da semana num dado intervalo
    public int numOfDaysOfWeek(Temporal t1, Temporal t2, DayOfWeek day);
    
    // numero de dias uteis, num dado intervalo
    public int numWorkingDays(Temporal t1, Temporal t2);

    // numero de dias da semana, num dado intervalo
    public int numNonWorkingDays(Temporal t1, Temporal t2);

    //Numero de fins de semana, num dado intervalo
    public int numWeekends(Temporal t1, Temporal t2);
    
    //Calcula o primeiro dia da semana de um ano
    public DayOfWeek getFistDayOfTheYear(Year y);

    //Calcula o ultimo dia da semana um ano
    public DayOfWeek getLastDayOfTheYear(Year y);

    //Calcula o dia da semana correspondente a uma data
    public DayOfWeek getDayOfTheWeek(TemporalAccessor data);

    //Calcula o mes correspondente a um dia do ano
    public Month getMonthOfYear(int dayOfYear, Year y);

    //Calcula quanto tempo falta para acabar o ano (dias)
    public long getTimeTillEndYear();
    
    //Calcula quanto tempo passou desde o inicio do ano
    public long getTimePassedStartYear();
    
    //Calcula os anos bissextos num dado seculo
    public List<Integer> getLeapYear(ChronoUnit unit, int value);

    //Calcula o tempo que passou relativamente a um dada unidade de tempo
    public Duration timeSince(ChronoUnit unit);

    //Calcula o tempo que falta para acabar uma dada unidade de tempo
    public Duration timeUntil(ChronoUnit unit);

    //Primeiro dia de uma dada unidade
    public LocalDate firstDayInfo(ChronoUnit unit, LocalDate date);

    //Ultimo dia de uma dada unidade
    public LocalDate lastDayInfo(ChronoUnit unit, LocalDate date);
}
