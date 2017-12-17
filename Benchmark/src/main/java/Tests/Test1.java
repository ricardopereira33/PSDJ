package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class Test1 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test1(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        // double[]
        double[] array = getArray(ltc);

        // 1. for
        Supplier<Double> supArray = () -> sumFor(array);
        AbstractMap.SimpleEntry<Double, Double> res = t.testeBoxGenW(supArray);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue());

        // 2. forEach
        Supplier<Double> supArray2 = () -> sumForEach(array);
        AbstractMap.SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supArray2);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue());

        /*** Sequenical ***/

        // DoubleStream
        DoubleStream ds = ltc.stream().mapToDouble(TransCaixa::getValor);
        Supplier<Double> supArray3 = () -> ds.sum();
        AbstractMap.SimpleEntry<Double, Double> res3 = t.testeBoxGenW(supArray3);
        System.out.println("Time: "+ res3.getKey() +"\t Res: " + res3.getValue());

        // Stream<Double>
        Stream<Double> sd = ltc.stream().map(TransCaixa::getValor);
        Supplier<Double> supArray4 = () -> sd.reduce(0.0, (v1, v2) -> v1 + v2);
        AbstractMap.SimpleEntry<Double, Double> res4 = t.testeBoxGenW(supArray4);
        System.out.println("Time: "+ res4.getKey() +"\t Res: " + res4.getValue());

        /*** Parallel ***/

        // DoubleStream
        DoubleStream dsp = ltc.parallelStream().mapToDouble(TransCaixa::getValor);
        Supplier<Double> supArray5 = () -> dsp.sum();
        AbstractMap.SimpleEntry<Double, Double> res5 = t.testeBoxGenW(supArray5);
        System.out.println("Time: "+ res5.getKey() +"\t Res: " + res5.getValue());

        // Stream<Double>
        Stream<Double> sdp = ltc.parallelStream().map(TransCaixa::getValor);
        Supplier<Double> supArray6 = () -> sdp.reduce(0.0, (v1, v2) -> v1 + v2);
        AbstractMap.SimpleEntry<Double, Double> res6 = t.testeBoxGenW(supArray6);
        System.out.println("Time: "+ res6.getKey() +"\t Res: " + res6.getValue());
    }

    private double sumForEach(double[] array){
        double total = 0;
        for (double v : array) {
            total += v;
        }
        return total;
    }

    private double sumFor(double[] array){
        double total = 0;
        for (int i = 0; i < array.length; i++)
            total += array[i];
        return total;
    }

    private double[] getArray(List<TransCaixa> ltc) {
        double[] list = new double[ltc.size()];
        int i = 0;
        for(TransCaixa t : ltc){
            list[i] = t.getValor();
            i++;
        }
        return list;
    }
}
