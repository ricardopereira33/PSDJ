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
import java.util.stream.DoubleStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        System.out.println("Sequencial - Spliterator");
        Supplier<Double> supSIStream = () -> splitTransCaixa(false);
        AbstractMap.SimpleEntry<Double, Double> res4 = t.testeBoxGenW(supSIStream);
        System.out.println("Time: "+ res4.getKey() +"\t | Res: " + res4.getValue());

        System.out.println("Parallel - Spliterator");
        Supplier<Double> supSIStreamP = () -> splitTransCaixa(true);
        AbstractMap.SimpleEntry<Double, Double> res5 = t.testeBoxGenW(supSIStreamP);
        System.out.println("Time: "+ res5.getKey() +"\t | Res: " + res5.getValue());
    }

    private double sumForEach(){
        double total = 0;
        for (TransCaixa tc : ltc) {
            double v = tc.getValor();
            total += v;
        }
        return total;
    }

    private double splitTransCaixa(boolean isParallel){
        List<Stream<TransCaixa>> list = new ArrayList<>();
        Spliterator<TransCaixa> si1_4 = ltc.spliterator();
        Spliterator<TransCaixa> si2_4 = si1_4.trySplit();
        Spliterator<TransCaixa> si3_4 = si1_4.trySplit();
        Spliterator<TransCaixa> si4_4 = si2_4.trySplit();

        list.add(StreamSupport.stream(si1_4, isParallel));
        list.add(StreamSupport.stream(si2_4, isParallel));
        list.add(StreamSupport.stream(si3_4, isParallel));
        list.add(StreamSupport.stream(si4_4, isParallel));

        double total = 0;
        for(Stream<TransCaixa> stc : list)
            total += stc.mapToDouble(TransCaixa::getValor).sum();

        return total;
    }
}
