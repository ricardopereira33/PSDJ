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

/**
 *
 * @author Ricardo
 */
public class ConverterMode implements Converter {

    @Override
    public ZonedDateTime timeIn(String zone) {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zone_id = ZoneId.of(zone);
        ZonedDateTime zone_date_time = now.atZone(zone_id);
        return zone_date_time;
    }

    @Override
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone) {
        ZoneId start_zone_id = ZoneId.of(startZone);
        ZonedDateTime start_date_time = start.atZone(start_zone_id);
        ZoneId end_zone_id = ZoneId.of(endZone);
        ZonedDateTime end_date_time = end.atZone(end_zone_id);
        Duration duration = Duration.between(start_date_time, end_date_time);
        return duration;
    }
    
    @Override
    public long converterUnit(ChronoUnit in, ChronoUnit out, long value){
        LocalDateTime time = LocalDateTime.now();
        LocalDateTime time2= time.plus(value,in);

        return out.between(time,time2);
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
