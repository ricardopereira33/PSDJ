/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Interfaces;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 *
 * @author Ricardo
 */
public interface TimeZoneConverter {
    /*devolve a hora atual numa dada zona*/
    public ZonedDateTime TimeIn(String zone);
    
    /*duração de uma viagem entre dois locais */
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone);
    
    
    
}
