package calendar.Interfaces;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public interface Interval {
    //Intervalo entre LocalDate ou LocalDateTime
    //public Duration getIntervalDateTime(Temporal d1, Temporal d2);

    //Calcula um conjunto de datas ou tempos distanciadas entre si por um dado intervalo
    public ArrayList<Temporal> getDates(Temporal start, Duration interval, int numDates);
    
    //Intervalo entre dois tempos, numa unidade dada
    public long getIntervalTimeUnit(Temporal t1, Temporal t2, ChronoUnit unit);
    
    // Converter um valor dado em uma unidade para outra unidade 
    public long converterUnit(ChronoUnit in, ChronoUnit out, long value);
    
    // numero de dias uteis, num dado intervalo
    public long numWorkingDays(Temporal t1, Temporal t2);

    // numero de dias da semana, num dado intervalo
    public long numNonWorkingDays(Temporal t1, Temporal t2);
    
    //Calcula o numero de vezes que um dia da semana existe num dado intervalo
    public int getNumDayOfWeek(DayOfWeek day, Temporal start, Temporal end);

    // Duração de uma viagem entre dois locais
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone);
}
