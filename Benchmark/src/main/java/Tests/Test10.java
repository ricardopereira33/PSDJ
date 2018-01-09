package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toMap;

public class Test10 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test10(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        //JAVA 7
        System.out.println("Java 7");
        Supplier<Map<Month,Double>> supForEach = () -> getIVAByMonth(ltc);
        AbstractMap.SimpleEntry<Double, Map<Month,Double>> res = t.testeBoxGenW(supForEach);
        System.out.println("Time: "+ res.getKey() +"\t | Res: " + res.getValue());

        //JAVA 8
        System.out.println("Java 8");

        Supplier<Map<Month,Double>> supStream = () -> {
            Map<Month,Double> ivaByMonth = new HashMap<>();
            ltc.stream().forEach(x -> {double value = x.getValor();
                double iva = 0;
                Month month = x.getData().getMonth();
                if(value < 20) iva = value*0.15;
                else if(value >= 20 && value <= 29) iva = value*0.20;
                else iva = value*0.23;
                if(ivaByMonth.containsKey(month)) {double actual = ivaByMonth.get(month);ivaByMonth.put(month,actual+iva);}
                else ivaByMonth.put(month,iva);
            });
            return ivaByMonth;
        };
        //Supplier<Map<Integer,Double>> supStream = () -> ltc.stream().collect(toMap(x -> x.getData().getMonth().getValue(),x ->x.getValor()*0.20,(oldValue, newValue) -> oldValue + newValue));
        AbstractMap.SimpleEntry<Double, Map<Month,Double>> res2 = t.testeBoxGenW(supStream);
        System.out.println("Time: "+ res2.getKey() +"\t | Res: " + res2.getValue());
    }

    private Map<Month,Double> getIVAByMonth(List<TransCaixa> ltc){
        Map<Month,Double> ivaByMonth = new HashMap<>();
        for(TransCaixa tc : ltc){
            LocalDateTime date = tc.getData();
            Month month = date.getMonth();
            double value = tc.getValor();
            double iva = 0;
            if(value < 20) iva = value*0.15;
            else if(value >= 20 && value <= 29) iva = value*0.20;
            else iva = value*0.23;
            if(ivaByMonth.containsKey(month)){
                double actual = ivaByMonth.get(month);
                ivaByMonth.put(month,actual+iva);
            }
            else ivaByMonth.put(month,iva);
        }
        return ivaByMonth;
    }
}
