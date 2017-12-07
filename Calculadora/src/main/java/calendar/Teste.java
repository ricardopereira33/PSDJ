package calendar;

import calendar.Interfaces.*;
import calendar.Modes.CalendarMode;
import calendar.Modes.IntervalMode;
import calendar.Presentation.CalendarInterface;
import calendar.Presentation.Home;
import calendar.Presentation.IntervalInterface;
import calendar.Presentation.TimeZoneInterface;
import calendar.Util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.time.ZoneId;
import java.util.TreeSet;



/**
 *
 * @author Ricardo
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //IntervalMode intervalMode = new IntervalMode(); 
        CalendarMode calendarMode = new CalendarMode();
        CalendarInterface home = new CalendarInterface(calendarMode);
        home.setVisible(true);
    }
}
