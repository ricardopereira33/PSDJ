package calendar.Interfaces;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;

public interface TimeZone {
    
    // Calcular a data/hora numa determinada região.
    public ZonedDateTime currentTimeIn(String zone);
    
    // Calcular a data/hora numa determinada região, dado a data/hora atual de uma outra região.
    public ZonedDateTime timeIn(LocalDateTime start, String startZone, String endZone);
    
    // Calcular a diferença de data/hora para duas região, dadas as suas data(s)/hora(s) atuais.
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone);
    
}
