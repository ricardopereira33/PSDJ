/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import calendar.Interfaces.Options;
import java.util.List;

/**
 *
 * @author dinispeixoto
 */
public class OptionsMode implements Options{
    
    String dateFormat;
    String timeFormat;
    List<Integer> durationFormat;
    
    public OptionsMode(String dateFormat, String timeFormat, List<Integer> durationFormat){
        this.dateFormat = dateFormat;
        this.timeFormat = timeFormat;
        this.durationFormat = durationFormat;
    }

    @Override
    public String getDateFormat() {
        return dateFormat;
    }

    @Override
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public String getTimeFormat() {
        return timeFormat;
    }

    @Override
    public void setTimeFormat(String dateTimeFormat) {
        this.timeFormat = dateTimeFormat;
    }

    @Override
    public List<Integer> getDurationFormat() {
        return durationFormat; 
    }

    @Override
    public void setDurationFormat(List<Integer> durationFormat) {
        this.durationFormat = durationFormat;
    }
    
    
    
}
