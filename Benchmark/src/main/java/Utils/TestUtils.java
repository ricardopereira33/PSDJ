package Utils;

import Structure.TransCaixa;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class TestUtils implements Interfaces.Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public TestUtils(List<TransCaixa> l, Tools t){
        this.ltc = new ArrayList<>();
        this.ltc.addAll(l);
        this.t = t;
    }

    @Override
    public void test1() {
        // double[]
        double[] array = getArray(ltc);

        // 1. for
        Supplier<Double> supArray = () -> sumFor(array);
        SimpleEntry<Double, Double> res = t.testeBoxGenW(supArray);
        System.out.println("Time: "+ res.getKey() +"\t | Res: " + res.getValue());

        // 2. forEach
        Supplier<Double> supArray2 = () -> sumForEach(array);
        SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supArray2);
        System.out.println("Time: "+ res2.getKey() +"\t | Res: " + res2.getValue());

        /*** Sequenical ***/

        // DoubleStream
        Supplier<Double> supArray3 = () -> ltc.stream().mapToDouble(TransCaixa::getValor).sum();
        SimpleEntry<Double, Double> res3 = t.testeBoxGenW(supArray3);
        System.out.println("Time: "+ res3.getKey() +"\t | Res: " + res3.getValue());

        // Stream<Double>
        Supplier<Double> supArray4 = () -> ltc.stream().map(TransCaixa::getValor).reduce(0.0, (v1, v2) -> v1 + v2);
        SimpleEntry<Double, Double> res4 = t.testeBoxGenW(supArray4);
        System.out.println("Time: "+ res4.getKey() +"\t | Res: " + res4.getValue());

        /*** Parallel ***/

        // DoubleStream
        Supplier<Double> supArray5 = () -> ltc.parallelStream().mapToDouble(TransCaixa::getValor).sum();
        SimpleEntry<Double, Double> res5 = t.testeBoxGenW(supArray5);
        System.out.println("Time: "+ res5.getKey() +"\t | Res: " + res5.getValue());

        // Stream<Double>
        Supplier<Double> supArray6 = () -> ltc.parallelStream().map(TransCaixa::getValor).reduce(0.0, (v1, v2) -> v1 + v2);
        SimpleEntry<Double, Double> res6 = t.testeBoxGenW(supArray6);
        System.out.println("Time: "+ res6.getKey() +"\t | Res: " + res6.getValue());
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

    @Override
    public void test2() {

    }

    @Override
    public void test3() {

    }

    @Override
    public void test4() {

    }

    @Override
    public void test5() {

    }

    @Override
    public void test6() {

    }

    @Override
    public void test7() {

    }

    @Override
    public void test8() {

    }

    @Override
    public void test9() {

    }

    @Override
    public void test10() {

    }

    @Override
    public void test11() {

    }

    @Override
    public void test12() {

    }
}
