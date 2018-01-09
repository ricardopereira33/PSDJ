package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap.SimpleEntry;
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
        System.out.println("# for");
        Supplier<Double> supArray = () -> sumFor(array);
        SimpleEntry<Double, Double> res = t.testeBoxGenW(supArray);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue() + "\n");

        // 2. forEach
        System.out.println("# forEach");
        Supplier<Double> supArray2 = () -> sumForEach(array);
        SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supArray2);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue()+ "\n");

        /*** Sequenical ***/

        // DoubleStream
        System.out.println("# Sequencial - DoubleStream");
        Supplier<Double> supArray3 = () -> ltc.stream().mapToDouble(TransCaixa::getValor).sum();
        SimpleEntry<Double, Double> res3 = t.testeBoxGenW(supArray3);
        System.out.println("Time: "+ res3.getKey() +"\t Res: " + res3.getValue()+ "\n");

        // Stream<Double>
        System.out.println("# Sequencial - Stream<Double>");
        Supplier<Double> supArray4 = () -> ltc.stream().map(TransCaixa::getValor).reduce(0.0, (v1, v2) -> v1 + v2);
        SimpleEntry<Double, Double> res4 = t.testeBoxGenW(supArray4);
        System.out.println("Time: "+ res4.getKey() +"\t Res: " + res4.getValue()+ "\n");

        /*** Parallel ***/

        // DoubleStream
        System.out.println("# Parallel - DoubleStream");
        Supplier<Double> supArray5 = () -> ltc.parallelStream().mapToDouble(TransCaixa::getValor).sum();
        SimpleEntry<Double, Double> res5 = t.testeBoxGenW(supArray5);
        System.out.println("Time: "+ res5.getKey() +"\t Res: " + res5.getValue()+ "\n");

        // Stream<Double>
        System.out.println("# Parallel - Stream<Double>");
        Supplier<Double> supArray6 = () -> ltc.parallelStream().map(TransCaixa::getValor).reduce(0.0, (v1, v2) -> v1 + v2);
        SimpleEntry<Double, Double> res6 = t.testeBoxGenW(supArray6);
        System.out.println("Time: "+ res6.getKey() +"\t Res: " + res6.getValue()+ "\n");
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
