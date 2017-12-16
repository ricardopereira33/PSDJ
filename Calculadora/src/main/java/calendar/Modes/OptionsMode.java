/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import calendar.Interfaces.Options;

/**
 *
 * @author dinispeixoto
 */
public class OptionsMode implements Options{
    
    String dateFormat;
    String timeFormat;
    
    public OptionsMode(String dateFormat, String timeFormat){
        this.dateFormat = dateFormat;
        this.timeFormat = timeFormat;
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
    
    
    
}
