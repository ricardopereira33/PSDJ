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

public class Test06 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test06(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        /*** Streams ***/
        // Tabela contendo todas as transacções catalogadas por Mês, Dia, Hora efectivos
        System.out.println("# Streams");
        Supplier<Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>>> supTable = () ->
                ltc.stream().collect(groupingBy(t -> t.getData().getMonth(),
                                     groupingBy(t -> t.getData().getDayOfMonth(),
                                     groupingBy(t -> t.getData().getHour()))));

        AbstractMap.SimpleEntry<Double, Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>>> res = t.testeBoxGenW(supTable);
        System.out.println("Time: "+ res.getKey() +"\t Res: " + res.getValue().size());

        /*** Java 7 ***/
        System.out.println("# Java 7");
        Supplier<Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>>> supTableJava7 = () -> getTable();
        AbstractMap.SimpleEntry<Double, Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>>> res2 = t.testeBoxGenW(supTableJava7);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue().size());
    }

    private Map<Month,Map<Integer,Map<Integer,List<TransCaixa>>>> getTable() {
        Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>> table = new HashMap<>();

        for(TransCaixa t : ltc){
            Month m = t.getData().getMonth();
            int day =  t.getData().getDayOfMonth();
            int hour = t.getData().getHour();
            Map<Integer, Map<Integer, List<TransCaixa>>> tableMonth;
            Map<Integer, List<TransCaixa>> tableDay;
            List<TransCaixa> listHour;

            tableMonth = getTableMonth(table, m);
            tableDay = getTableDay(tableMonth, day);
            listHour = getListHour(tableDay, hour);

            listHour.add(t);
        }

        return table;
    }

    private List<TransCaixa> getListHour(Map<Integer, List<TransCaixa>> table, int hour) {
        List<TransCaixa> listHour;
        if(table.containsKey(hour))
            listHour = table.get(hour);
        else  {
            listHour = new ArrayList<>();
            table.put(hour, listHour);
        }

        return listHour;
    }

    private Map<Integer,List<TransCaixa>> getTableDay(Map<Integer, Map<Integer, List<TransCaixa>>> table, int day) {
        Map<Integer, List<TransCaixa>> tableDay;
        if(table.containsKey(day))
            tableDay = table.get(day);
        else  {
            tableDay = new HashMap<>();
            table.put(day, tableDay);
        }

        return tableDay;
    }

    private Map<Integer, Map<Integer, List<TransCaixa>>> getTableMonth(Map<Month, Map<Integer, Map<Integer, List<TransCaixa>>>> table, Month m) {
        Map<Integer, Map<Integer, List<TransCaixa>>> tableMonth;
        if(table.containsKey(m))
            tableMonth = table.get(m);
        else  {
            tableMonth = new HashMap<>();
            table.put(m,tableMonth);
        }

        return tableMonth;
    }
}
