package calendar;

import calendar.Interfaces.*;
import calendar.Modes.CalendarMode;
import calendar.Modes.ChronometerMode;
import calendar.Modes.IntervalMode;
import calendar.Modes.OptionsMode;
import calendar.Modes.TimeZoneMode;
import calendar.Presentation.CalendarInterface;
import calendar.Presentation.Menu;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){

        String configuration_file = "Calculator.conf";
        Interval intervalMode = new IntervalMode(); 
        Calendar calendarMode = new CalendarMode();
        TimeZone timeZoneMode = new TimeZoneMode();
        Chronometer chronometerMode = new ChronometerMode();
        
        // Default options
        
        Options optionsMode;
        try {
            optionsMode = new OptionsMode();
            optionsMode = optionsMode.importOptions(configuration_file);
        } catch (Exception ex) {
            optionsMode = new OptionsMode(configuration_file,"EEE dd-MM-yyyy","HH:mm:ss",Arrays.asList(0,1,1,1,0,0));
        }
        
        Menu home = new Menu(intervalMode, calendarMode, timeZoneMode, chronometerMode, optionsMode);
        home.setVisible(true);
        CalendarInterface calendarInterface = new CalendarInterface(calendarMode, optionsMode);
        calendarInterface.setVisible(true);
    }
}
