package calendar;

import calendar.Interfaces.*;
import calendar.Modes.*;
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
        try{
            Util_Datas.ex6();
        }
        catch(Exception e){
            System.out.println("Msg: "+ e.getMessage());
        }
    }
    
}
