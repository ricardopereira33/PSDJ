package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap;
import java.util.List;
import java.util.Spliterator;
import java.util.*;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

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
        System.out.println("forEach()");
        Supplier<Double> supList = () -> sumForEach();
        AbstractMap.SimpleEntry<Double, Double> res = t.testeBoxGenW(supList);
        System.out.println("Time: "+ res.getKey() +"\t | Res: " + res.getValue());

        // DoubleStream Sequencial
        System.out.println("Stream Sequencial");
        //DoubleStream ds = ltc.stream().mapToDouble(TransCaixa::getValor);
        Supplier<Double> supStream = () -> ltc.stream().mapToDouble(TransCaixa::getValor).sum();
        AbstractMap.SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supStream);
        System.out.println("Time: "+ res2.getKey() +"\t | Res: " + res2.getValue());

        // DoubleStream Paralelo
        System.out.println("Stream Paralela");
        //DoubleStream ds2 = ltc.parallelStream().mapToDouble(TransCaixa::getValor);
        Supplier<Double> supPStream = () -> ltc.parallelStream().mapToDouble(TransCaixa::getValor).sum();
        AbstractMap.SimpleEntry<Double, Double> res3 = t.testeBoxGenW(supPStream);
        System.out.println("Time: "+ res3.getKey() +"\t | Res: " + res3.getValue());

        //4 partiçoes
        System.out.println("Spliterator");
        Spliterator.OfDouble siOf = ltc.stream().mapToDouble(TransCaixa::getValor).spliterator();
        Spliterator.OfDouble siOf1 = siOf.trySplit();
        Spliterator.OfDouble siOf2 = siOf.trySplit();
        Spliterator.OfDouble siOf3 = siOf1.trySplit();
        
    }

    private double sumForEach(){
        double total = 0;
        for (TransCaixa tc : ltc) {
            double v = tc.getValor();
            total += v;
        }
        return total;
    }
}
