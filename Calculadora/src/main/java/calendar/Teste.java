package calendar;

import calendar.Interfaces.*;
import calendar.Modes.CalendarMode;
import calendar.Modes.ChronometerMode;
import calendar.Modes.IntervalMode;
import calendar.Modes.TimeZoneMode;
import calendar.Presentation.Menu;
import calendar.Util.*;



/**
 *
 * @author Ricardo
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        IntervalMode intervalMode = new IntervalMode(); 
        CalendarMode calendarMode = new CalendarMode();
        TimeZoneMode timeZoneMode = new TimeZoneMode();
        Chronometer chrono = new ChronometerMode();
        Menu home = new Menu(intervalMode, calendarMode, timeZoneMode, chrono);
        home.setVisible(true);
    }
}
