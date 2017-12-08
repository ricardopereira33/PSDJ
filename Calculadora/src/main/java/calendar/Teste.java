package calendar;

import calendar.Interfaces.*;
import calendar.Modes.CalendarMode;
import calendar.Modes.IntervalMode;
import calendar.Modes.TimeZoneMode;
import calendar.Presentation.CalendarInterface;
import calendar.Presentation.Home;
import calendar.Presentation.IntervalInterface;
import calendar.Presentation.Menu;
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
        Interval intervalMode = new IntervalMode(); 
        Calendar calendarMode = new CalendarMode();
        TimeZone timeZoneMode = new TimeZoneMode();
        Menu home = new Menu(intervalMode, calendarMode, timeZoneMode);
        home.setVisible(true);
    }
}
