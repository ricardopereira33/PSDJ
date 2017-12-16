package calendar.Interfaces;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public interface TimeZone {
    // Devolve a hora atual numa dada zona 
    public ZonedDateTime currentTimeIn(String zone);
    
    // Devolve a data/hora numa dada zona
    public ZonedDateTime timeIn(LocalDateTime start, String startZone, String endZone);
    
    // Duração de uma viagem entre dois locais
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone);
    
}
