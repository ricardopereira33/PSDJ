package Utils;

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
    
    public static void memoryUsage(){
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
}
