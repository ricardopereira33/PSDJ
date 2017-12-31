package calendar.Interfaces;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public interface Interval {
    
    // Calcular o intervalo de tempo entre dois tempos, para uma dada unidade.
    public long getIntervalTimeUnit(Temporal t1, Temporal t2, ChronoUnit unit);
    
    // Converter um valor dado numa unidade para outra. 
    public double converterUnit(ChronoUnit in, ChronoUnit out, long value);
    
    // Dado uma data/tempo adicionar uma determinada unidade de tempo (e.g. somar 2 horas). 
    public LocalDateTime addDateTime(LocalDateTime d, ChronoUnit unit, int value);
    
    // Dado uma data/tempo subtrair uma determinada unidade de tempo (e.g. subtrair 2 horas).
    public LocalDateTime subDateTime(LocalDateTime d, ChronoUnit unit, int value);
}
