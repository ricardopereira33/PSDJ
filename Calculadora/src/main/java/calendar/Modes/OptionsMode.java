/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import calendar.Interfaces.Options;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.Duration;
import java.util.List;

/**
 *
 * @author dinispeixoto
 */
public class OptionsMode implements Options, Serializable{
    
    String configurationFile;
    String dateFormat;
    String timeFormat;
    List<Integer> durationFormat;
    
    public OptionsMode(){    
    }
    
    public OptionsMode(String configFile, String dateFormat, String timeFormat, List<Integer> durationFormat){
        this.configurationFile = configFile;
        this.dateFormat = dateFormat;
        this.timeFormat = timeFormat;
        this.durationFormat = durationFormat;
    }

    public String getConfigurationFile() {
        return configurationFile;
    }

    public void setConfigurationFile(String configurationFile) {
        this.configurationFile = configurationFile;
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

    @Override
    public String durationToString(Duration duration) {
        StringBuilder durationInfo = new StringBuilder();
        
        durationInfo.append("Duration: " + duration.toString()
            .substring(2)
            .replaceAll("(\\d[HMS])(?!$)", "$1 ")
            .toLowerCase());
        
        durationInfo.append("\n");
        if(durationFormat.get(0) == 1)
            durationInfo.append("Days: " + duration.toDays() + "\n");
        if(durationFormat.get(1) == 1)
            durationInfo.append("Hours: " + duration.toHours() + "\n");
        if(durationFormat.get(2) == 1)
            durationInfo.append("Minutes: " + duration.toMinutes() + "\n");
        if(durationFormat.get(3) == 1)
            durationInfo.append("Seconds: " + duration.getSeconds() + "\n");
        if(durationFormat.get(4) == 1)
            durationInfo.append("Milliseconds: " + duration.toMillis() + "\n");
        if(durationFormat.get(5) == 1)
            durationInfo.append("Nanoseconds: " + duration.toNanos() + "\n");
        return durationInfo.toString();
    }

    /**
     *
     * @param file
     * @throws IOException
     */
    @Override
    public void exportOptions() throws Exception{
        ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(configurationFile));
        obj.writeObject(this);
        obj.flush();
        obj.close();
    }
    
    
    
}
