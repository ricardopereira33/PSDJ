package calendar.Interfaces;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAccessor;
import java.util.List;

public interface Calendar {
    
    // Número de vezes que um determinado dia da semana ocorre entre um determinado intervalo de dias.
    public int numOfDaysOfWeek(Temporal t1, Temporal t2, DayOfWeek day);
    
    // Número de dias de trabalho entre um determinado intervalo de dias.
    public int numWorkingDays(Temporal t1, Temporal t2);

    // Número de dias de descanço (dias que não são de trabalho) entre um determinado intervalo de dias.
    public int numNonWorkingDays(Temporal t1, Temporal t2);

    // Número de fins-de-semana que ocorrem num determinado intervalo de dias.
    public int numWeekends(Temporal t1, Temporal t2);

    // Calcula o tempo que passou relativamente a uma dada unidade de tempo.
    public Duration timeSince(ChronoUnit unit);

    // Calcula o tempo que falta para acabar uma dada unidade de tempo.
    public Duration timeUntil(ChronoUnit unit);

    // Primeiro dia para uma dada unidade (e.g primeiro dia de um determinado mês).
    public LocalDate firstDayInfo(ChronoUnit unit, LocalDate date);

    // Último dia para uma dada unidade (e.g último dia de um determinado mês).
    public LocalDate lastDayInfo(ChronoUnit unit, LocalDate date);
}
