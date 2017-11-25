import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class IntervalMode implements Interval{

    public Duration getIntervalDateTime(Temporal d1, Temporal d2){
        return Duration.between(d1, d2);
    }

    public ArrayList<Temporal> getDates(Temporal start, Duration interval, int numDates){
        ArrayList<Temporal> dates = new ArrayList<>();
        Temporal date = start.plus(interval);
        dates.add(date);
        for(int i=1;i<numDates;i++){
            date = date.plus(interval);
            dates.add(date);
        }
        return dates;
    }
}
