package Utils;

import Structure.TransCaixa;

import java.time.Month;
import java.util.*;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.Supplier;

import static Structure.Caixa.transPorData;
import static Structure.Caixa.transPorData2;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;
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
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue());

        // 2. forEach
        Supplier<Double> supArray2 = () -> sumForEach(array);
        SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supArray2);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue());

        /*** Sequenical ***/

        // DoubleStream
        Supplier<Double> supArray3 = () -> ltc.stream().mapToDouble(TransCaixa::getValor).sum();
        SimpleEntry<Double, Double> res3 = t.testeBoxGenW(supArray3);
        System.out.println("Time: "+ res3.getKey() +"\t Res: " + res3.getValue());

        // Stream<Double>
        Supplier<Double> supArray4 = () -> ltc.stream().map(TransCaixa::getValor).reduce(0.0, (v1, v2) -> v1 + v2);
        SimpleEntry<Double, Double> res4 = t.testeBoxGenW(supArray4);
        System.out.println("Time: "+ res4.getKey() +"\t Res: " + res4.getValue());

        /*** Parallel ***/

        // DoubleStream
        Supplier<Double> supArray5 = () -> ltc.parallelStream().mapToDouble(TransCaixa::getValor).sum();
        SimpleEntry<Double, Double> res5 = t.testeBoxGenW(supArray5);
        System.out.println("Time: "+ res5.getKey() +"\t Res: " + res5.getValue());

        // Stream<Double>
        Supplier<Double> supArray6 = () -> ltc.parallelStream().map(TransCaixa::getValor).reduce(0.0, (v1, v2) -> v1 + v2);
        SimpleEntry<Double, Double> res6 = t.testeBoxGenW(supArray6);
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

    @Override
    public void test2() {
        int indexF20 = (ltc.size()/5) +1;

        // TreeSet
        TreeSet<TransCaixa> transOrdData = new TreeSet<>(transPorData);
        transOrdData.addAll(ltc);

        // List
        List<TransCaixa> transOrdDataList = ltc.stream().sorted(transPorData)
                                                        .collect(toList());
        /*** Sequenical ***/

        // List
        Supplier<List<List<TransCaixa>>> supListSeq = () -> getSubSetsList(transOrdDataList, indexF20);
        SimpleEntry<Double, List<List<TransCaixa>>> res = t.testeBoxGenW(supListSeq);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue().get(0).get(0));

        // TreeSet
        Supplier<List<TreeSet<TransCaixa>>> supTreeSeq = () -> getSubSetsTree(transOrdData, indexF20);
        SimpleEntry<Double, List<TreeSet<TransCaixa>>> res2 = t.testeBoxGenW(supTreeSeq);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue().get(0).first());

        /*** Parallel ***/

        // List
        Supplier<List<List<TransCaixa>>> supListPar = () -> getSubSetsListP(transOrdDataList, indexF20);
        SimpleEntry<Double, List<List<TransCaixa>>> res3 = t.testeBoxGenW(supListPar);
        System.out.println("Time: "+ res3.getKey() +"\t Res: " + res3.getValue().get(0).get(0));

        // TreeSet
        Supplier<List<TreeSet<TransCaixa>>> supTreePar2 = () -> getSubSetsTreeP(transOrdData, indexF20);
        SimpleEntry<Double, List<TreeSet<TransCaixa>>> res4 = t.testeBoxGenW(supTreePar2);
        System.out.println("Time: "+ res4.getKey() +"\t Res: " + res4.getValue().get(0).first());
    }

    private List<TreeSet<TransCaixa>> getSubSetsTree(TreeSet<TransCaixa> transOrdDataTree, int indexF20){
        TreeSet<TransCaixa> first20T = transOrdDataTree.stream().limit(indexF20)
                                                                .collect(toCollection(() -> new TreeSet<>(transPorData)));
        TreeSet<TransCaixa> last20T = transOrdDataTree.stream().sorted(transPorData2)
                                                                .limit(indexF20)
                                                                .collect(toCollection(() -> new TreeSet<>(transPorData2)));
        List<TreeSet<TransCaixa>> res = new ArrayList<>();
        res.add(first20T);
        res.add(last20T);
        return res;
    }

    private List<List<TransCaixa>> getSubSetsList(List<TransCaixa> transOrdDataList, int indexF20) {
        List<TransCaixa> first20L = transOrdDataList.stream().limit(indexF20)
                                                             .collect(toCollection(() -> new ArrayList<>()));
        List<TransCaixa> last20L = transOrdDataList.stream().sorted(transPorData2)
                                                            .limit(indexF20)
                                                            .collect(toCollection(() -> new ArrayList<>()));
        List<List<TransCaixa>> res = new ArrayList<>();
        res.add(first20L);
        res.add(last20L);
        return res;
    }

    private List<TreeSet<TransCaixa>> getSubSetsTreeP(TreeSet<TransCaixa> transOrdDataTree, int indexF20){
        TreeSet<TransCaixa> first20T = transOrdDataTree.parallelStream().limit(indexF20)
                .collect(toCollection(() -> new TreeSet<>(transPorData)));
        TreeSet<TransCaixa> last20T = transOrdDataTree.parallelStream().sorted(transPorData2)
                .limit(indexF20)
                .collect(toCollection(() -> new TreeSet<>(transPorData2)));
        List<TreeSet<TransCaixa>> res = new ArrayList<>();
        res.add(first20T);
        res.add(last20T);
        return res;
    }

    private List<List<TransCaixa>> getSubSetsListP(List<TransCaixa> transOrdDataList, int indexF20) {
        List<TransCaixa> first20L = transOrdDataList.parallelStream().limit(indexF20)
                .collect(toCollection(() -> new ArrayList<>()));
        List<TransCaixa> last20L = transOrdDataList.parallelStream().sorted(transPorData2)
                .limit(indexF20)
                .collect(toCollection(() -> new ArrayList<>()));
        List<List<TransCaixa>> res = new ArrayList<>();
        res.add(first20L);
        res.add(last20L);
        return res;
    }

    @Override
    public void test3() {
        // int[]

        // IntStream

        // List<Integer>
    }

    @Override
    public void test4() {

    }

    @Override
    public void test5() {
        // List
        Supplier<TreeSet<TransCaixa>> supTree = () -> ltc.stream().collect(toCollection(() -> new TreeSet<>(transPorData)));
        SimpleEntry<Double, TreeSet<TransCaixa>> res = t.testeBoxGenW(supTree);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue().first());

        // TreeSet
        Supplier<List<TransCaixa>> supList = () -> ltc.stream().sorted(transPorData).collect(toList());
        SimpleEntry<Double, List<TransCaixa>> res2 = t.testeBoxGenW(supList);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue().get(0));
    }

    @Override
    public void test6() {
        //aula

        Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>> mapaTxPorMDH = ltc.stream()
                                                                                    .collect(groupingBy(t -> t.getData().getMonth(),
                                                                                            groupingBy(t -> t.getData().getDayOfMonth(),
                                                                                            groupingBy(t -> t.getData().getHour()))));

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
