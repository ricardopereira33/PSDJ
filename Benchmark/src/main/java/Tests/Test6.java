package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.time.Month;
import java.util.*;
import java.util.function.Supplier;

import static Structure.Caixa.transPorData;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;

public class Test6 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test6(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        /*** Streams ***/
        // Tabela contendo todas as transacções catalogadas por Mês, Dia, Hora efectivos,
        Supplier<Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>>> supTable = () ->
                ltc.stream().collect(groupingBy(t -> t.getData().getMonth(),
                                     groupingBy(t -> t.getData().getDayOfMonth(),
                                     groupingBy(t -> t.getData().getHour()))));

        AbstractMap.SimpleEntry<Double, Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>>> res = t.testeBoxGenW(supTable);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue().get(0).get(1).get(1));

        /*** Java 7 ***/


    }
}
