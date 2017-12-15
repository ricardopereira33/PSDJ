package Utils;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author asus
 */

import Structure.TransCaixa;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.temporal.TemporalAccessor;
import java.time.*;
import static Structure.Caixa.strToTransCaixa;
import static Structure.Caixa.transPorData;
import static java.lang.System.out;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.Collectors.counting;
import static java.util.Comparator.comparing;
import java.util.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import static java.time.temporal.ChronoField.DAY_OF_WEEK;
import java.util.AbstractMap.SimpleEntry;
import java.util.function.*;


public class Tools {
    
    public static void memoryUsage() {
        final int mByte = 1024*1024;
        // Parâmetros de RunTime
        Runtime runtime = Runtime.getRuntime();
        out.println("== Valores de Utilização da HEAP [MB] ==");
        out.println("Memória Máxima RT:" + runtime.maxMemory()/mByte);
        out.println("Total Memory:" + runtime.totalMemory()/mByte);
        out.println("Memória Livre:" + runtime.freeMemory()/mByte);
        out.println("Memoria Usada:" + (runtime.totalMemory() - runtime.freeMemory())/mByte);	
    }
   
    public static List<TransCaixa> setupStream(String nomeFich) {
      List<TransCaixa> ltc = new ArrayList<>();
      try (Stream<String> sTrans = Files.lines(Paths.get(nomeFich))) {
               ltc = sTrans.map(linha -> strToTransCaixa(linha)).collect(toList());
      } 
      catch(IOException exc) {
          out.println(exc.getMessage());
      }
      return ltc;
    }
    
    public static List<TransCaixa> setupNStream(String nomeFich) {
      List<String> lines = new ArrayList<>();
      try {
          lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8);
      }
      catch(IOException exc) {
          System.out.println(exc.getMessage());
      }

      List<TransCaixa> lTrans = new ArrayList<>();
      lines.forEach(line -> lTrans.add(strToTransCaixa(line)));
      return lTrans;
    }
    
    public static <R> SimpleEntry<Double,R> testeBoxGenW(Supplier<? extends R> supplier) {
        // warmup
        for(int i = 1 ; i <= 5; i++)
            supplier.get();
        System.gc();
        Crono.start();
        R resultado = supplier.get();
        Double tempo = Crono.stop();
        return new SimpleEntry<Double,R>(tempo, resultado);
     }
    
    public static void main(String[] args) {
        
        String nomeFich = "transCaixa1M.txt";
        List<TransCaixa> ltc = new ArrayList<>();
        
        // LE O FICHEIRO DE TRANSACÇOES PARA List<Structure.TransCaixa> sem Streams
        Crono.start();
        ltc = setupNStream(nomeFich);
        out.println("Setup com List<String>: " + Crono.stop()*1000 + " ms");
        out.println("Transacções lidas Lists: " + ltc.size());
        ltc.clear();
       
        // LE O FICHEIRO DE TRANSACÇOES PARA List<Structure.TransCaixa> com Streams
        Crono.start();
        ltc = setupStream(nomeFich);
        out.println("Setup com Streams: " + Crono.stop()*1000 + " ms");
        out.println("Transacções lidas (Streams): " + ltc.size());
        memoryUsage();
        
          
        // EXERCICIOS
        
        /* Transações a 0 
        Utils.Crono.start();
        long num = ltc.stream().filter( t -> t.getValor() == 0.0)
                               .count();
        out.println("Setup com Streams: " + Utils.Crono.stop()*1000 + " ms");
        System.out.println("Num: "+ num);
       
        Utils.Crono.start();
        boolean nun = ltc.stream().anyMatch( t -> t.getValor() == 0.0);
        out.println("Setup com Streams: " + Utils.Crono.stop()*1000 + " ms");
        
        System.out.println("Existe: "+nun);
        */
        /****/
        
        /* Caixas com transações*/
        /*Utils.Crono.start();
        List<Integer> set = ltc.stream().map(t -> Integer.valueOf(t.getCaixa()))
                                        .distinct()
                                        .sorted((l1,l2) -> l1.compareTo(l2))
                                        .collect(toList());
        int numCaixas = set.size();
                                    
        out.println("Setup com Streams: " + Utils.Crono.stop()*1000 + " ms");
        System.out.println("Num: "+ numCaixas +"\t\tFirst: "+set.get(0)+"\tLast: "+set.get(numCaixas-1));
        */
        
        //Número de transações de valor entre valMin e valMax ([valMin,valMax]). Generalizar a solução usando funções
        /*
        Function<Double, Predicate<Structure.TransCaixa>> valTransMaiorQue = val -> t -> t.getValor() >= val;
        Function<Double, Predicate<Structure.TransCaixa>> valTransMenorQue = val -> t-> t.getValor() <= val;
        BiFunction<Double, Double, Predicate<Structure.TransCaixa>> predValTransMaiorOuMenorQue = (val1, val2) -> valTransMaiorQue.apply(val1).and(valTransMenorQue.apply(val2));
        
        final double valMin = 13.5; final double valMax = 20.0;
        
        long numTrans = ltc.stream().filter(t->predValTransMaiorOuMenorQue.apply(valMin,valMax).test(t)).count();
        
        System.out.println("numTrans: "+ numTrans);
        */
        /******/
        Crono.start();
        SortedSet<TransCaixa> transOrdData = new TreeSet<>(transPorData);
        transOrdData.addAll(ltc);
        out.println(transOrdData.first() + " - " + transOrdData.last() +"\t time: "+Crono.stop());

        /****/
        Crono.start();
        List<TransCaixa> transOrdData2 =
                ltc.stream()
                        .sorted(transPorData)
                        .collect(toList());
        out.println(transOrdData2.get(0) + " - " + transOrdData2.get(transOrdData2.size()-1) +"\t time: "+Crono.stop());

        /****/
        Crono.start();
        Supplier<SortedSet<TransCaixa>> supplyTreeSetTcx = () -> new TreeSet<>(transPorData);
        SortedSet<TransCaixa> transOrdData3 = ltc.stream()
                .collect(toCollection(supplyTreeSetTcx));
        out.println(transOrdData3.first() + " - " + transOrdData3.last()+"\t time: "+Crono.stop());

        /****/
        Crono.start();
        Map<String, List<TransCaixa>> tabCxTrans500 =
                ltc.stream()
                        .sorted(transPorData)
                        .limit(500)
                        .collect(groupingBy(TransCaixa::getCaixa));
        tabCxTrans500.forEach((cx, ltcx) -> out.println("Structure.Caixa " + cx + " #Trans: " + ltcx.size()));
        System.out.println("\t time: " + Crono.stop());

        /****/
        Crono.start();
        Map<String, Long> tabNumTransCaixa =
                ltc.stream()
                        .collect(groupingBy(TransCaixa::getCaixa, TreeMap::new, counting()));
        tabNumTransCaixa.forEach( (cx, nt) -> out.println("Structure.Caixa: " + cx + " --> " + nt));
        System.out.println("\t time: " + Crono.stop());

        /****/
        Crono.start();
        Map<String, Double> tabCaixaValor = ltc.stream()
                                                .collect(groupingBy(TransCaixa::getCaixa, summingDouble(TransCaixa::getValor)));
        tabCaixaValor.forEach((nr, fact) -> out.println("Structure.Caixa: "+ nr + "-> "+fact));
        System.out.println("\t time: " + Crono.stop());

        /***/
        Crono.start();
        List<SimpleEntry<String,Double>> lis = ltc.stream()
                                                    .map(t -> new SimpleEntry<String,Double>(t.getCaixa(),t.getValor())).collect(toList());
        Map<String, Double> m = new HashMap<>();
        m = lis.stream().collect(groupingBy(p -> p.getKey(), summingDouble(p -> p.getValue())));
        System.out.println("\t time: " + Crono.stop());

        /***/
        Crono.start();
        Map<DayOfWeek, Double> m2 = ltc.stream().collect(groupingBy(t -> diaDaSemana(t.getData().toLocalDate()), summingDouble(TransCaixa::getValor)));
        System.out.println("\t time: " + Crono.stop());
        m2.forEach((diaSem,fact) -> out.println("Dias: " + diaSem + " -> "+fact));

        /*****/
        Crono.start();
        Function<TransCaixa, Long> txToSeconds = t -> {
            LocalDateTime lt = t.getData();
            return lt.query(Tools::parseSegundos);
        };

        final String numCaixa = "14";
        List<Long> tempoEmSecs = ltc.stream().filter(t -> t.getCaixa().equals(numCaixa))
                                                .sorted(transPorData)
                                                .map(t -> txToSeconds.apply(t))
                                                .collect(toList());
        System.out.println("\t time: " + Crono.stop());



    }

    public static DayOfWeek diaDaSemana(TemporalAccessor tacs){
        try{
            LocalDate dataRef = LocalDate.from(tacs);
        }
        catch(DateTimeException e){
            return null;
        }
        return DayOfWeek.of(tacs.get(DAY_OF_WEEK));
    }

    public static Long parseSegundos(TemporalAccessor tacs){
        LocalDateTime dataRef = null;
        try{
            dataRef = LocalDateTime.from(tacs);
        }
        catch(DateTimeException e){
            return null;
        }
        return dataRef.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }
}
