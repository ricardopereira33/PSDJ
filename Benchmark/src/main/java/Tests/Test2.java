package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Supplier;

import static Structure.Caixa.transPorData;
import static Structure.Caixa.transPorData2;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class Test2 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test2(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        int indexF20 = (ltc.size()/5) + 1;

        // TreeSet
        TreeSet<TransCaixa> transOrdData = new TreeSet<>(transPorData);
        transOrdData.addAll(ltc);

        // List
        List<TransCaixa> transOrdDataList = ltc.stream().sorted(transPorData)
                .collect(toList());
        /*** Sequenical ***/

        // List
        Supplier<List<List<TransCaixa>>> supListSeq = () -> getSubSetsList(transOrdDataList, indexF20);
        AbstractMap.SimpleEntry<Double, List<List<TransCaixa>>> res = t.testeBoxGenW(supListSeq);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue().get(0).get(0));

        // TreeSet
        Supplier<List<TreeSet<TransCaixa>>> supTreeSeq = () -> getSubSetsTree(transOrdData, indexF20);
        AbstractMap.SimpleEntry<Double, List<TreeSet<TransCaixa>>> res2 = t.testeBoxGenW(supTreeSeq);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue().get(0).first());

        /*** Parallel ***/

        // List
        Supplier<List<List<TransCaixa>>> supListPar = () -> getSubSetsListP(transOrdDataList, indexF20);
        AbstractMap.SimpleEntry<Double, List<List<TransCaixa>>> res3 = t.testeBoxGenW(supListPar);
        System.out.println("Time: "+ res3.getKey() +"\t Res: " + res3.getValue().get(0).get(0));

        // TreeSet
        Supplier<List<TreeSet<TransCaixa>>> supTreePar2 = () -> getSubSetsTreeP(transOrdData, indexF20);
        AbstractMap.SimpleEntry<Double, List<TreeSet<TransCaixa>>> res4 = t.testeBoxGenW(supTreePar2);
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
}
