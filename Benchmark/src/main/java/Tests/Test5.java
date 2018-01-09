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
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toList;

public class Test5 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test5(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        // List
        System.out.println("# List");
        Supplier<TreeSet<TransCaixa>> supTree = () -> ltc.stream().collect(toCollection(() -> new TreeSet<>(transPorData)));
        AbstractMap.SimpleEntry<Double, TreeSet<TransCaixa>> res = t.testeBoxGenW(supTree);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue().first());

        // TreeSet
        System.out.println("# TreeSet");
        Supplier<List<TransCaixa>> supList = () -> ltc.stream().sorted(transPorData).collect(toList());
        AbstractMap.SimpleEntry<Double, List<TransCaixa>> res2 = t.testeBoxGenW(supList);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue().get(0));
    }
}
