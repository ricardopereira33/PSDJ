package calendar.Interfaces;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public interface Interval {
    //Intervalo entre LocalDate ou LocalDateTime
    public Duration getIntervalDateTime(Temporal d1, Temporal d2);

    //Calcula um conjunto de datas ou tempos distanciadas entre si por um dado intervalo
    public ArrayList<Temporal> getDates(Temporal start, Duration interval, int numDates);
    
    //Intervalo entre dois tempos, numa unidade dada
    public long getIntervalTimeUnit(Temporal t1, Temporal t2, ChronoUnit unit);
    
    // numero de dias uteis, num dado intervalo
    public int numWorkingDays(Temporal t1, Temporal t2);
    
    
}
