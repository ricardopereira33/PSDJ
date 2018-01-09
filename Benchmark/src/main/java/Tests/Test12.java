package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

public class Test12 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test12(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        Map<String,Map<Integer,Set<TransCaixa>>> transByCaixaMap = getTransByCaixa(false);
        Map<String,Map<Integer,Set<TransCaixa>>> transByCaixaConcMap = getTransByCaixa(true);

        Supplier<Double> supStreamMap = () -> transByCaixaMap.values().stream().mapToDouble(x -> x.values().stream().mapToDouble(h -> h.stream().mapToDouble(i -> i.getValor()).sum()).sum()).sum();
        AbstractMap.SimpleEntry<Double, Double> res = t.testeBoxGenW(supStreamMap);
        System.out.println("Time: "+ res.getKey() +"\t | Res: " + res.getValue());

        Supplier<Double> supStreamConcMap = () -> transByCaixaConcMap.values().stream().mapToDouble(x -> x.values().stream().mapToDouble(h -> h.stream().mapToDouble(i -> i.getValor()).sum()).sum()).sum();
        AbstractMap.SimpleEntry<Double, Double> res2 = t.testeBoxGenW(supStreamConcMap);
        System.out.println("Time: "+ res2.getKey() +"\t | Res: " + res2.getValue());
    }

    private Map<String,Map<Integer,Set<TransCaixa>>> getTransByCaixa(boolean concurrentMap){
        Map<String,Map<Integer,Set<TransCaixa>>> transByCaixa;
        if(!concurrentMap) transByCaixa = new HashMap<>();
        else transByCaixa = new ConcurrentHashMap<>();
        for(TransCaixa tc : ltc){
            String caixa = tc.getCaixa();
            int month = tc.getData().getMonth().getValue();
            Map<Integer,Set<TransCaixa>> caixaByMonth;
            Set<TransCaixa> caixaInMonth;
            if(transByCaixa.containsKey(caixa)) {
                caixaByMonth = transByCaixa.get(caixa);
            }
            else if(!concurrentMap)caixaByMonth = new HashMap<>();
            else caixaByMonth = new ConcurrentHashMap<>();
            if(caixaByMonth.containsKey(month)){
                caixaInMonth = caixaByMonth.get(month);
            }
            else {
                caixaInMonth = new HashSet<>();
            }
            caixaInMonth.add(tc);
            caixaByMonth.put(month,caixaInMonth);
            transByCaixa.put(caixa,caixaByMonth);
        }
        return transByCaixa;
    }
}
