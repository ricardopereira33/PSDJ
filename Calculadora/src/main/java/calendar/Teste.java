package calendar;

import calendar.Interfaces.*;
import calendar.Modes.*;
import calendar.Util.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 *
 * @author Ricardo
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            LocalDateTime data = LocalDateTime.now();
            LocalDateTime end = LocalDateTime.of(2017, 12, 31, 23, 59);
            //System.out.println(data.query(Util_Datas::actualHour));
            
            Calendar c = new CalendarMode();
            Interval i = new IntervalMode();
            Converter converter = new ConverterMode();
            //System.out.println("Num: " + i.numWorkingDays(data, end));

            ArrayList<Integer> list = (ArrayList<Integer>) c.getLeapYear(ChronoUnit.CENTURIES, 20);
            for(Integer o : list)
                System.out.println("> "+ o + " .");
        }
        catch(Exception e){
            System.out.println("Msg: "+ e.getMessage());
        }
    }
    
}
