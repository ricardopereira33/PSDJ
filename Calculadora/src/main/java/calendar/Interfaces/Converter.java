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

public interface Converter {
    // Devolve a hora atual numa dada zona 
    public ZonedDateTime timeIn(String zone);
    
    // Converter um valor dado em uma unidade para outra unidade 
    public long converterUnit(ChronoUnit in, ChronoUnit out, long value);
    
    // Dado uma data/tempo, adiciona p.e. dias,minutos,hora, etc.. 
    public LocalDateTime addDateTime(LocalDateTime d, ChronoUnit unit, int value);
    
    // Dado uma data/tempo, adiciona p.e. dias,minutos,hora, etc.. 
    public LocalDateTime subDateTime(LocalDateTime d, ChronoUnit unit, int value);
}
