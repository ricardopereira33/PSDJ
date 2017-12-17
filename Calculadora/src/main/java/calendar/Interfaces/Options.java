/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Interfaces;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.util.List;

/**
 *
 * @author dinispeixoto
 */
public interface Options {
    
    public String getConfigurationFile();
    public void setConfigurationFile(String file);
    public String getDateFormat();
    public void setDateFormat(String dateFormat);
    public String getTimeFormat();
    public void setTimeFormat(String dateFormat);
    public List<Integer> getDurationFormat();
    public void setDurationFormat(List<Integer> durationFormat);
    public String durationToString(Duration duration);
    public void exportOptions() throws Exception;
    
    public static Options importOptions(String file) throws Exception{
        ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file));
        Options options = (Options) obj.readObject();
        obj.close();
        return options;
    }
}
