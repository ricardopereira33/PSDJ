package calendar.Interfaces;

import java.time.*;
import java.time.temporal.TemporalAccessor;

public interface Calendar {
    //Calcula o primeiro dia da semana de um ano
    public DayOfWeek getFistDayOfTheYear(Year y);

    //Calcula o ultimo dia da semana um ano
    public DayOfWeek getLastDayOfTheYear(Year y);

    //Calcula o dia da semana correspondente a uma data
    public DayOfWeek getDayOfTheWeek(TemporalAccessor temporal);

    //Calcula o mes correspondente a um dia do ano
    public Month getMonthOfYear(int dayOfYear, Year y);
    

}
