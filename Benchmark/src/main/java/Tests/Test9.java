package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

import static java.time.temporal.ChronoField.ALIGNED_WEEK_OF_YEAR;

public class Test9 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test9(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        //Para a semana 30 do ano 2017
        List<List<TransCaixa>> tcOnWeek = getTcByWeekDays(30);

        //JAVA 7
        System.out.println("Java 7");
        Supplier<Double> supForEach = () -> getTotalInWeek(tcOnWeek);
        AbstractMap.SimpleEntry<Double, Double> res = t.testeBoxGenW(supForEach);
        System.out.println("Time: "+ res.getKey() +"\t | Res: " + res.getValue());

        //JAVA 8
        System.out.println("Java 8");
        Supplier<Double> supStream = () -> tcOnWeek.stream().mapToDouble(x -> {return x.stream().mapToDouble(TransCaixa::getValor).sum();}).sum();
        AbstractMap.SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supStream);
        System.out.println("Time: "+ res2.getKey() +"\t | Res: " + res2.getValue());
    }

    private List<List<TransCaixa>> getTcByWeekDays(int week){
        List<List<TransCaixa>> tcByWeekDays = new ArrayList<>();
        for(int i = 0; i<7; i++){
            List<TransCaixa> weekList = new ArrayList<>();
            tcByWeekDays.add(i,weekList);
        }
        for(TransCaixa tc : ltc){
            TemporalField weekOfYear = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear();
            LocalDateTime date = tc.getData();
            if(date.getYear() == 2017){
                int numWeek = date.toLocalDate().get(ALIGNED_WEEK_OF_YEAR);
                if(numWeek == week) {
                    int dayOfWeek = date.getDayOfWeek().getValue();
                    tcByWeekDays.get(dayOfWeek-1).add(tc);
                }
            }
        }
        return tcByWeekDays;
    }

    private double getTotalInWeek(List<List<TransCaixa>> tcByWeekDays){
        double total=0;
        for(List<TransCaixa> tcWeekDay : tcByWeekDays){
            for(TransCaixa tc : tcWeekDay){
                total += tc.getValor();
            }
        }
        return total;
    }
}
