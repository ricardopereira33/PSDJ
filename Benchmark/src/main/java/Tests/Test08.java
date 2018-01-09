package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;
import java.util.function.Supplier;

public class Test08 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test08(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        //JAVA 7
        System.out.println("Java 7");
        Supplier<OptionalDouble> supForEach = () -> getTransCaixaBetweenHours(ltc);
        AbstractMap.SimpleEntry<Double, OptionalDouble> res = t.testeBoxGenW(supForEach);
        System.out.println("Time: "+ res.getKey() +"\t | Res: " + res.getValue());

        //JAVA 8
        System.out.println("Java 8");
        Supplier<OptionalDouble> supStream = () -> ltc.stream().filter(tc -> isTransCaixaBetweenHours(tc)).mapToDouble(TransCaixa::getValor).max();
        AbstractMap.SimpleEntry<Double, OptionalDouble> res2 = t.testeBoxGenW(supStream);
        System.out.println("Time: "+ res2.getKey() +"\t | Res: " + res2.getValue());
    }

    private boolean isTransCaixaBetweenHours(TransCaixa tc){
        if(tc.getData().getHour() <= 20 && tc.getData().getHour() >= 16)
            if(tc.getData().getHour() == 20 && tc.getData().getMinute() > 0)return false;
            else return true;
        else return false;
    }

    private OptionalDouble getTransCaixaBetweenHours(List<TransCaixa> ltc){
        List<TransCaixa> list = new ArrayList<>();
        for(TransCaixa tc : ltc){
            if(isTransCaixaBetweenHours(tc)) list.add(tc);
        }
        double max = -1;
        for(TransCaixa tc : ltc){
            if(tc.getValor() > max) max = tc.getValor();
        }
        if(max == -1) return OptionalDouble.empty();
        else return OptionalDouble.of(max);
    }
}
