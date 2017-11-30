package calendar;

import calendar.Interfaces.*;
import calendar.Modes.*;
import calendar.Util.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;


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
            System.out.println(data.query(Util_Datas::actualHour));
            
            Calendar c = new CalendarMode();
            Interval i = new IntervalMode();
            System.out.println("Num: " + i.numWorkingDays(data, end));
        }
        catch(Exception e){
            System.out.println("Msg: "+ e.getMessage());
        }
    }
    
}
