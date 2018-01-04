package calendar.Interfaces;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.util.List;


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
    public Options importOptions(String file) throws Exception;
}
