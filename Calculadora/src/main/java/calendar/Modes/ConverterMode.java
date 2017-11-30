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
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Ricardo
 */
public class ConverterMode implements Converter {

    @Override
    public ZonedDateTime TimeIn(String zone) {
        return null;
    }

    @Override
    public Duration timeTravel(LocalDateTime start, String startZone, LocalDateTime end, String endZone) {
        return null;
    }
    
    @Override
    public long converterUnit(ChronoUnit in,ChronoUnit out, long value){
        return 0;
    }

    @Override
    public LocalDate addDate(LocalDate d, ChronoUnit unit, int value) {
        return null;
    }

    @Override
    public LocalDate subDate(LocalDate d, ChronoUnit unit, int value) {
        return null;
    }
    
    
    
    
}
