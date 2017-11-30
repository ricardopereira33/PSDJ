/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Interfaces;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Ricardo
 */
public interface Converter {
    // Devolve a hora atual numa dada zona 
    public ZonedDateTime TimeIn(String zone);
    
    // Duração de uma viagem entre dois locais 
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone);
    
    // Converter um valor dado em uma unidade para outra unidade 
    public long converterUnit(ChronoUnit in, ChronoUnit out, long value);
    
    // Dado uma data/tempo, adiciona p.e. dias,minutos,hora, etc.. 
    public LocalDate addDate(LocalDate d, ChronoUnit unit, int value);
    
    // Dado uma data/tempo, adiciona p.e. dias,minutos,hora, etc.. 
    public LocalDate subDate(LocalDate d, ChronoUnit unit, int value);
    
    
    
    
}
