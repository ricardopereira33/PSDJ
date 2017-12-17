package calendar.Modes;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import calendar.Interfaces.TimeZone;

public class TimeZoneMode implements TimeZone {

    public TimeZoneMode(){
        
    }
    
    @Override
    public ZonedDateTime currentTimeIn(String zone) {
        LocalDateTime now = LocalDateTime.now();
        ZoneId zone_id = ZoneId.of(zone);
        ZonedDateTime zone_date_time = now.atZone(zone_id);
        return zone_date_time;
    }
    
    @Override
    public ZonedDateTime timeIn(LocalDateTime start, String startZone, String endZone){
        ZoneId start_zone_id = ZoneId.of(startZone);
        ZonedDateTime start_date_time = start.atZone(start_zone_id);
        ZoneId end_zone_id = ZoneId.of(endZone);
        ZonedDateTime end_date_time = start_date_time.withZoneSameInstant(end_zone_id);
        return end_date_time;
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


}
