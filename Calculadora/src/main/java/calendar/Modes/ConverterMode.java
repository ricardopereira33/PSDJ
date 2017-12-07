/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import calendar.Interfaces.Converter;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class ConverterMode implements Converter {

    @Override
    public ZonedDateTime timeIn(String zone) {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zone_id = ZoneId.of(zone);
        ZonedDateTime zone_date_time = now.atZone(zone_id);
        return zone_date_time;
    }
    

    @Override
    public LocalDateTime addDateTime(LocalDateTime date_time, ChronoUnit unit, int value) {
        LocalDateTime result = unit.addTo(date_time, value);
        return result;
    }

    @Override
    public LocalDateTime subDateTime(LocalDateTime date_time, ChronoUnit unit, int value) {
        LocalDateTime result = unit.addTo(date_time, -value);
        return result;
    }
}
