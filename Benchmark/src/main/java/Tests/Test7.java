package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

public class Test7 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test7(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        //Data set inteiro
        //List
        List<Double> list = ltc.stream().map(TransCaixa::getValor).collect(toList());

        Supplier<Double> supList = () -> sumForEachList(list);
        AbstractMap.SimpleEntry<Double, Double> res = t.testeBoxGenW(supList);
        System.out.println("Time: "+ res.getKey() +"\t | Res: " + res.getValue());

        // DoubleStream Sequencial
        DoubleStream ds = ltc.stream().mapToDouble(TransCaixa::getValor);
        Supplier<Double> supStream = () -> ds.sum();
        AbstractMap.SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supStream);
        System.out.println("Time: "+ res2.getKey() +"\t | Res: " + res2.getValue());

        // DoubleStream Paralelo
        DoubleStream ds2 = ltc.parallelStream().mapToDouble(TransCaixa::getValor);
        Supplier<Double> supPStream = () -> ds2.sum();
        AbstractMap.SimpleEntry<Double, Double> res3 = t.testeBoxGenW(supPStream);
        System.out.println("Time: "+ res3.getKey() +"\t | Res: " + res3.getValue());

        //4 partiçoes
        Spliterator.OfDouble siOf = ltc.stream().mapToDouble(TransCaixa::getValor).spliterator();
        Spliterator.OfDouble siOf1 = siOf.trySplit();
        Spliterator.OfDouble siOf2 = siOf.trySplit();
        Spliterator.OfDouble siOf3 = siOf1.trySplit();

        
    }

    private double sumForEachList(List<Double> list){
        double total = 0;
        for (double v : list) {
            total += v;
        }
        return total;
    }
}
