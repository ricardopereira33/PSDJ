package calendar;

import calendar.Interfaces.*;
import calendar.Modes.CalendarMode;
import calendar.Modes.ChronometerMode;
import calendar.Modes.IntervalMode;
import calendar.Modes.OptionsMode;
import calendar.Modes.TimeZoneMode;
import calendar.Presentation.CalendarInterface;
import calendar.Presentation.Menu;
import calendar.Util.*;
import java.util.Arrays;

/**
 *
 * @author Ricardo
 */
public class Calculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Interval intervalMode = new IntervalMode(); 
        Calendar calendarMode = new CalendarMode();
        TimeZone timeZoneMode = new TimeZoneMode();
        Chronometer chronometerMode = new ChronometerMode();
        
        // Default options
        Options optionsMode = new OptionsMode("EEE dd-MM-yyyy","HH:mm:ss",Arrays.asList(0,1,1,1,0,0));
        
        Menu home = new Menu(intervalMode, calendarMode, timeZoneMode, chronometerMode, optionsMode);
        home.setVisible(true);
        CalendarInterface calendarInterface = new CalendarInterface(calendarMode, optionsMode);
        calendarInterface.setVisible(true);
    }
}
