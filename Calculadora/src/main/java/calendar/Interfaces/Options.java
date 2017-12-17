/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Interfaces;

import java.time.Duration;
import java.util.List;

/**
 *
 * @author dinispeixoto
 */
public interface Options {
    
    public String getDateFormat();
    public void setDateFormat(String dateFormat);
    public String getTimeFormat();
    public void setTimeFormat(String dateFormat);
    public List<Integer> getDurationFormat();
    public void setDurationFormat(List<Integer> durationFormat);
    public String durationToString(Duration duration);
}
