package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;

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
        Spliterator<TransCaixa> s = ltc.spliterator();
        Spliterator<TransCaixa> s1 = s.trySplit();
        Spliterator<TransCaixa> s2 = s.trySplit();
        Spliterator<TransCaixa> s3 = s1.trySplit();
        Set<Spliterator<TransCaixa>> set = new HashSet(Arrays.asList(s,s1,s2,s3));
        ForkJoinPool f = new ForkJoinPool(4);
        //Callable<List<Double>> c = () -> set.stream().map(x -> x.forEachRemaining(TransCaixa::getValor)).collect(toList());


        s.forEachRemaining(TransCaixa::getValor);
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
