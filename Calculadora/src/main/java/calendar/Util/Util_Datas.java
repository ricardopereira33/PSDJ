package calendar.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.DateTimeException;
import java.time.DayOfWeek;
import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.TUESDAY;
import static java.time.DayOfWeek.WEDNESDAY;
import static java.time.DayOfWeek.THURSDAY;
import static java.time.DayOfWeek.FRIDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.MinguoDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import static java.time.format.DateTimeFormatter.ISO_DATE;
import static java.time.format.DateTimeFormatter.ISO_TIME;
import static java.time.format.DateTimeFormatter.ofPattern;


/**
 *
 * @author Ricardo
 */
public class Util_Datas {
    
    public Util_Datas(){ }

    public static Instant ToInstant(TemporalAccessor temporal){
        LocalDateTime date;
        try{
            date = LocalDateTime.from(temporal);
        }
        catch(DateTimeException e){
            return null;
        } 
        
        Instant inst = date.toInstant(ZoneOffset.UTC);
        return inst;
    }
    
    public static String actualHour(TemporalAccessor temporal){
        LocalDateTime dateLDT;
        String date, time, offset;
        
        try{
            dateLDT = LocalDateTime.from(temporal);            
        }
        catch(DateTimeException e){
            return null;
        }
        
        date = dateLDT.format(ISO_DATE);
        time = dateLDT.format(ISO_TIME);
        
        ZoneId id = ZoneId.systemDefault();
        ZonedDateTime zdt = ZonedDateTime.of(dateLDT, id);

        return "Date: "+ date +"\tTime: "+ time +"\tOffset: " + zdt.getOffset();
    }
    
    public static void ex6(){
        LocalDateTime time1 = LocalDateTime.of(1996, Month.JUNE, 24, 9, 0,0,0);
        LocalDateTime time2 = LocalDateTime.of(1994, Month.FEBRUARY, 4, 22, 30,0,0);
        
        System.out.println("------------------------------------------");
        for(ChronoUnit unit : ChronoUnit.values()){
            if(unit.isSupportedBy(time1)){
                System.out.println("Duration: " + unit.between(time2, time1) + "\t" + unit); 
            }
        }
        System.out.println("------------------------------------------");
    }
    
    public static void ex2(){
        Duration d = Duration.ofHours(1);
        Duration d2 = Duration.of(1, ChronoUnit.HOURS);
        
        System.out.println("Min: " + d.toMinutes() + "\tSec: " + d.getSeconds() + "\tNano: " + d.toNanos());         
        System.out.println("Min: " + d2.toMinutes() + "\tSec: " + d2.getSeconds() + "\tNano: " + d2.toNanos());    
    }
    
    public static void ex3(){
        LocalDate eu = LocalDate.of(1996, 6, 24);
        LocalDate pai = eu.minusYears(33).minusMonths(6);        
        LocalDate mae = eu.minusYears(31).minusMonths(9);
        LocalDate agora = LocalDate.now();
        
        System.out.println("Idade(Eu) : " + Period.between(eu, agora).getYears());        
        System.out.println("Idade(Pai): " + Period.between(pai, agora).getYears());
        System.out.println("Idade(Mae): " + Period.between(mae, agora).getYears());
        
        LocalDate irma = eu.plusYears(3).plusMonths(1).plusDays(25);
        
        System.out.println("Data: " + irma);
        System.out.println("Idade(Irma): " + Period.between(irma, agora).getYears());
    }
    
    public static void ex4(){
        LocalDateTime time1 = LocalDateTime.of(1996, Month.JUNE, 24, 9, 0,0,0);
        LocalDateTime time2 = LocalDateTime.of(1994, Month.FEBRUARY, 4, 22, 30,0,0);
        
        Duration d = Duration.between(time2, time1);
        Period p = Period.between(time2.toLocalDate(), time1.toLocalDate());
        
        System.out.println("------------------------------------------");
        for(TemporalUnit unit : d.getUnits()){
            System.out.println("Duration: " + d.get(unit) + "\t" + unit ); 
        }
        System.out.println("------------------------------------------");
        
        System.out.println("------------------------------------------");
        for(TemporalUnit unit : p.getUnits()){
            System.out.println("Period: " + p.get(unit) + "\t" + unit ); 
        }
        System.out.println("------------------------------------------");
        
    }
    
    public static void ex5(){
        LocalDate hoje = LocalDate.now();
        
        System.out.println("Era: " + hoje.getEra());
    }
   
    public static void ex7(){
        LocalDate seguro = LocalDate.of(2018, Month.JULY, 14);
        LocalDate hoje = LocalDate.now();
        
        long semanas = ChronoUnit.WEEKS.between(hoje, seguro);
        long dias = ChronoUnit.DAYS.between(hoje, seguro);
        
        System.out.println("Semanas " + semanas);
        System.out.println("Dias " + dias);
    }
    
    public static void ex8(){
        LocalDate segCarro = LocalDate.of(2017, Month.DECEMBER, 17);
        LocalDate hoje = LocalDate.of(2017, 1, 1);
        
        long semanas = ChronoUnit.WEEKS.between(hoje, segCarro);
        long dias = ChronoUnit.DAYS.between(hoje, segCarro);
        long semanas1 = hoje.until(segCarro, ChronoUnit.WEEKS);
        long dias1 = hoje.until(segCarro, ChronoUnit.DAYS);
	
        System.out.println("Faltam " + semanas + " semanas, ou seja " + dias + " dias");
        System.out.println("Faltam " + semanas1 + " semanas, ou seja " + dias1 + " dias");
        
        int diaDaSemana = segCarro.getDayOfWeek().getValue();
        System.out.println("Termina no dia da semana = " + diaDaSemana + " que é " + segCarro.getDayOfWeek());
        
        System.out.println("Devo pagar em : " +  segCarro.minusDays(diaDaSemana - 1));
        System.out.println("Pagar em : " + segCarro.with(TemporalAdjusters.previous(MONDAY)));
        
        // com importações
        System.out.println("Pagar na data " + segCarro.with(TemporalAdjusters.previous(MONDAY)));
    }
    
    public static void ex9(){
        LocalTime agora = LocalTime.now();
        System.out.println("Agora : " + agora);
        ChronoField[] campos = ChronoField.values();
        System.out.println("----- ChronoFields suportados por LocalTime -----");
        
        for(ChronoField cf : campos) 
              if(agora.isSupported(cf)) 
                  System.out.println(cf);
        
        System.out.println(agora.get(ChronoField.MINUTE_OF_DAY));
        System.out.println(agora.get(ChronoField.MINUTE_OF_HOUR));
        System.out.println(agora.getLong(ChronoField.NANO_OF_DAY));

        System.out.println("Não existe ChronoUnit correspondente a MINUTE_OF_DAY !");
        System.out.println("Gama : " + ChronoField.MINUTE_OF_DAY.range() + " Data tem MINUTE_OF_DAY ? " 
                            + LocalDate.now().isSupported(ChronoField.MINUTE_OF_DAY));
    }
    
    public static void ex10(){
        Instant agora = Instant.now();
        ZoneOffset zoffPortugal = OffsetDateTime.now().getOffset();
        ZonedDateTime agoraNossaZona = ZonedDateTime.ofInstant(agora, zoffPortugal);
        System.out.println(agoraNossaZona);
        
        System.out.println("----------------");
        for(Map.Entry<String,String> e : ZoneId.SHORT_IDS.entrySet()){
            System.out.println("Key: "+e.getKey()+"\tValue: "+e.getValue()+".");
        }
        System.out.println("----------------");

        
        ZoneId zonaAustralia = ZoneId.of("Australia/Canberra" );
        ZonedDateTime zdtAust = agora.atZone(zonaAustralia);
        System.out.println(zdtAust);
        
        System.out.println("Instante cf. Portugal : " + agora);
        System.out.println("Instante cf. Austrália : " + zdtAust.toInstant());
        System.out.println("---   TIRE AS CONCLUSÕES ---");
    }
            
    public static void ex11(){
       
    }
    
    public static void ex12(){
        LocalDate data = LocalDate.of(2017, Month.SEPTEMBER, 30);
        System.out.println("Inicio Seguro: " + data);
        System.out.println("Trimestre: " + data.get(IsoFields.QUARTER_OF_YEAR));
        System.out.println("Trimestre v.2: " + IsoFields.QUARTER_OF_YEAR.getFrom(data));
        
        System.out.println("---- Com Meses ----");
        for(int i = 1; i <= 3; i++){
            System.out.println("Pagamentos : " + (i+1) + " em " + data.plusMonths(3*i));
        }
        //cuidado que passa do intervalo 1..4
        System.out.println("--- Com quarter of year ----");
        for(int i = 1; i <= 3 ; i++){
            System.out.println(IsoFields.QUARTER_OF_YEAR.adjustInto(data, IsoFields.QUARTER_OF_YEAR.getFrom(data) + i));
        }
    
    }
    
    public static void ex13(){
        double valorDias = 29.530589;
        int diasTotais = (int) valorDias;
        double restoDias = valorDias - diasTotais;
        double diasTotaisEmSecs = diasTotais*24*60*60;
        double restoDiasEmSecs = restoDias*24*60*60;
        double totalEmSecs = diasTotaisEmSecs + restoDiasEmSecs;
        System.out.println("Total em Secs : " + totalEmSecs);
        
        Duration dura = Duration.of((long) totalEmSecs, ChronoUnit.SECONDS);
        System.out.println("Duração Total : " + dura);
        
        System.out.println("SECONDS do Duration: " + dura.getSeconds());
        System.out.println("NANOS da Duration : " + dura.getNano());
    }
    
    public static void ex14(){
       
    }
    
    public static void ex15(){
        LocalDateTime agora = LocalDateTime.of(2017, Month.DECEMBER, 31, 23, 30, 0);
        
        ZoneId santi = ZoneId.of("America/Santiago");
        ZonedDateTime santiDT = ZonedDateTime.of(agora, santi);
        
        System.out.println("Aqui : " + agora);
        System.out.println("Santiago : " + santiDT);
        
        OffsetDateTime odt = santiDT.toOffsetDateTime();
        System.out.println("-> " + odt);
        
        System.out.println("1- " + odt.atZoneSameInstant(ZoneId.of("Portugal")));
        System.out.println("2- " + santiDT.withZoneSameInstant(ZoneId.of("Portugal")));
        System.out.println("3- " + odt.atZoneSameInstant(ZoneId.of("Asia/Macau")));
        System.out.println("4- " + santiDT.withZoneSameInstant(ZoneId.of("Asia/Macau")));

    }
    
    public static void ex16(){
        LocalDate hoje = LocalDate.now();
        LocalDate fimDoAno = LocalDate.of(2018,1,1);
        TemporalAdjuster lastDay = TemporalAdjusters.lastDayOfYear();
        int total = 0;
        TemporalAdjuster next = TemporalAdjusters.next(WEDNESDAY);
  
        while(hoje.isBefore(fimDoAno)){
            int dia = hoje.getDayOfWeek().getValue();
            
            if(dia == 3){
                System.out.println("Data: " + hoje);
                total += 5;
            }
            hoje = hoje.plusDays(1);
        }
        
        System.out.println("Total : " + total);
    }
    
    public static void ex17(){
       
    }
    
    public static void ex18(){
        LocalDate data = LocalDate.of(2017, 4, 1);
        LocalDate primDiaAno = data.with(TemporalAdjusters.firstDayOfYear());
        LocalDate fimAno = data.with(TemporalAdjusters.lastDayOfYear());
        System.out.println("--- ChronoUnit WEEKS ---");
        System.out.println("Semana Actual em WEEKS : " + 
		ChronoUnit.WEEKS.between(primDiaAno, data));
        System.out.println("Semanas do Ano Actual em WEEKS : " +
		ChronoUnit.WEEKS.between(primDiaAno, fimAno));
        System.out.println("Semanas até ao fim do ano em WEEKS : " +
		ChronoUnit.WEEKS.between(data, fimAno));
        System.out.println("Dia no Ano : " + data.getDayOfYear());
        System.out.println("Semana no Ano via Dias : " + data.getDayOfYear()/7);
        System.out.println("--- IsoField WEEK_OF_WEEK_BASED_YEAR ---");
        System.out.println("Semana Actual com getLong : " + data.getLong(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        System.out.println("Semana Actual em W-WBY : " + IsoFields.WEEK_OF_WEEK_BASED_YEAR.getFrom(data));
        System.out.println("Semanas do Ano usando W-WBY : " +
	 	IsoFields.WEEK_OF_WEEK_BASED_YEAR.getFrom(fimAno));
        System.out.println("--- WeekFields.ISO ---");
        System.out.println("Semanas desde o inicio do ano com ISO : " +
	          WeekFields.ISO.weekOfWeekBasedYear().getFrom(data));
    }
    
    public static void ex19(){
        // É preciso saber o dia em que começa a 1ª semana de Dezembro 
        YearMonth ym = YearMonth.of(2017, 12);
        LocalDate primDiaSemDez = ym.atDay(1).with(TemporalAdjusters.firstInMonth(MONDAY));
        System.out.println("1ª semana de Dezembro começa a : " + 
            primDiaSemDez + " e não a 1/12/2017"); 
        System.out.println("O 3º dia da 2ª semana é : " +  
            primDiaSemDez.plus(1, ChronoUnit.WEEKS).plusDays(2));
    }
    
    public static void ex20(){
       
    }
    
    public static void ex21(){
       
    }
    
    public static void ex22(){
       
    }
    
    public static void ex23(){
        // DEZ DIAS UTEIS MAIS TARDE
        LocalDate dataRef = LocalDate.of(2017, 12, 25);
        int conta = 0;  // conta dias úteis
        while(conta < 10) {
            DayOfWeek dia = dataRef.getDayOfWeek();
            if(! (dia.equals(SATURDAY) || dia.equals(SUNDAY)))  conta++; 
            dataRef = dataRef.plus(1, ChronoUnit.DAYS);
        }
        
        System.out.println("Data após 10 dias úteis : " + dataRef);
    }
    
    public static void ex24(){
        int ano = 2017;
        int diasDoAno = Year.of(ano).length();
        System.out.println("Nº de dias do ano de " + ano + " = " + diasDoAno);
        LocalDate inicioAno = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
        LocalDate meioDoAno = inicioAno.plusDays(diasDoAno/2);
        System.out.println("Data de meio-do-ano : " + meioDoAno);
        System.out.println("Dias passados desde o início : " +
                     ChronoUnit.DAYS.between(inicioAno, meioDoAno));

    }
    
    public static void ex25(){
       
    }
    
    public static void ex26(){
        LocalDate localDate = LocalDate.of(1975, 4, 25);
        MinguoDate minguo = MinguoDate.from(localDate);
        System.out.println("Data Local : " + localDate);
        System.out.println("Data Minguo Correspondente : " + minguo); 
       
        System.out.println("-- Conversão Inversa --");
        
        MinguoDate minguoDate = MinguoDate.of(64, 4, 25);
        System.out.println("Data Minguo : " + minguo);   
        System.out.println("ERA Minguo : " + minguo.getEra());
        System.out.println("Dia da Época : " + minguo.toEpochDay());
        
        LocalDate localDate1 = LocalDate.from(minguo);
        System.out.println("Data Local : " + localDate1); 


    }
    
    public static void ex27(){
       
    }
    
    public static void ex28(){
        // Criar um ajustador que ajuste qualquer data para 15 dias antes.
        // Comparar com um que ajuste para 2 semanas antes.
        TemporalAdjuster menos15Dias = 
              TemporalAdjusters.ofDateAdjuster( (LocalDate data) -> data.minusDays(15) ); 
        TemporalAdjuster menos2Semanas = 
              TemporalAdjusters.ofDateAdjuster( (LocalDate data) -> data.minusWeeks(2) ); 
        LocalDate hoje = LocalDate.of(2017, 1, 1); 
        
        LocalDate hojeMenos15D = hoje.with(menos15Dias); 
        LocalDate hojeMenos2S = hoje.with(menos2Semanas); 
        System.out.println(" Hoje " + hoje); 
        System.out.println(" Hoje - 15 DAYS : " + hojeMenos15D);
        System.out.println(" Hoje - 2 WEEKS : " + hojeMenos2S);
    }
    
    public static void ex29(){
       
    }
    
    public static void ex30(){
       Comparator<LocalDate> compMenorData = 
           (LocalDate ld1, LocalDate ld2) -> { if(ld1.equals(ld2)) return 0;
                                               else if(ld1.isBefore(ld2)) return -1; 
                                                    else return 1 ; 
                                              };
        List<LocalDate> datas = 
                Arrays.asList( LocalDate.of(2014, 12, 1), LocalDate.of(2009, 2, 21), 
                               LocalDate.of(2017, 1, 1), LocalDate.of(2015, 4, 3), 
                               LocalDate.of(2011, 7, 12) );
        
        System.out.println("-- Usando TreeSet --");
        SortedSet<LocalDate> datasOrd = new TreeSet<>(compMenorData);
        datasOrd.addAll(datas);
        
        for(LocalDate ld : datasOrd) 
            System.out.println(ld + " ");
        
        System.out.println("\n-- Usando Collections --");
        Collections.sort(datas, compMenorData);
        
        for(LocalDate ld : datas) 
            System.out.println(ld + " ");

    }
 }
