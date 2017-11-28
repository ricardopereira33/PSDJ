/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import calendar.Interfaces.TimeZoneConverter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 *
 * @author Ricardo
 */
public class TimeZoneConverterMode implements TimeZoneConverter {

    @Override
    public ZonedDateTime TimeIn(String zone) {
        return null;
    }

    @Override
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone) {
        return null;
    }
    
}
