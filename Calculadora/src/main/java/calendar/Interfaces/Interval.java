package calendar.Interfaces;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public interface Interval {
    //Calcula um conjunto de datas ou tempos distanciadas entre si por um dado intervalo
    public ArrayList<Temporal> getDates(Temporal start, Duration interval, int numDates);
    
    //Intervalo entre dois tempos, numa unidade dada
    public long getIntervalTimeUnit(Temporal t1, Temporal t2, ChronoUnit unit);
    
    // Converter um valor dado em uma unidade para outra unidade 
    public double converterUnit(ChronoUnit in, ChronoUnit out, long value);
    
    //Calcula o numero de vezes que um dia da semana existe num dado intervalo
    public int getNumDayOfWeek(DayOfWeek day, Temporal start, Temporal end);
    
    // Dado uma data/tempo, adiciona p.e. dias,minutos,hora, etc.. 
    public LocalDateTime addDateTime(LocalDateTime d, ChronoUnit unit, int value);
    
    // Dado uma data/tempo, adiciona p.e. dias,minutos,hora, etc.. 
    public LocalDateTime subDateTime(LocalDateTime d, ChronoUnit unit, int value);
}
