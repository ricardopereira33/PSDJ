package Utils;

import Structure.TransCaixa;

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
        this.ltc = new ArrayList<TransCaixa>();
        this.ltc.addAll(l);
        this.t = t;
    }

    @Override
    public void test1() {
        // double[]
        double[] array = getArray(ltc);
        double total = 0;
        for(int i = 0; i < array.length; i++)
            total += array[i];

        // DoubleStream
        double res = ltc.stream().mapToDouble(TransCaixa::getValor).sum();

        // Stream<Double>
        Stream<Double> d = ltc.stream().map(TransCaixa::getValor);
        double res2 = d.reduce(0.0, (v1, v2) -> v1 + v2);
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
