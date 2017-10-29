/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caixa;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
/**
 *
 * @author Ricardo
 */
public class Caixa {
    
    /**
     * Converter string para TransCaixa
     */
    public static TransCaixa strToTransCaixa(String linha) {
       //     
       double preco = 0.0; 
       int ano = 0; int mes = 0; int dia = 0; 
       int horas = 0; int min = 0; int seg = 0;
       String codTrans, codCaixa;
       
       // split()
       String[] campos = linha.split("/");
       
       codTrans = campos[0].trim();
       codCaixa = campos[1].trim();
       
       try {
             preco = Double.parseDouble(campos[2]); 
       }
       catch(InputMismatchException | NumberFormatException e)
      { return null; } 
               
       String[] diaMesAnoHMS = campos[3].split("T");
       String[] diaMesAno = diaMesAnoHMS[0].split(":");
       String[] horasMin = diaMesAnoHMS[1].split(":");
       try {
             dia = Integer.parseInt(diaMesAno[0]);
             mes = Integer.parseInt(diaMesAno[1]);
             ano = Integer.parseInt(diaMesAno[2]);
             horas = Integer.parseInt(horasMin[0]);
             min = Integer.parseInt(horasMin[1]);
       }
       catch(InputMismatchException | NumberFormatException e) 
       { return null; } 
             
       return TransCaixa.of(codTrans, codCaixa, preco, LocalDateTime.of(ano, mes, dia, horas, min, 0));    
    }
    /*
    * Comparador para o TransCaixa
    */
    private static Comparator<TransCaixa> transPorData = 
      (TransCaixa tc1, TransCaixa tc2) -> { 
            LocalDateTime ldt1 = tc1.getData();
            LocalDateTime ldt2 = tc2.getData();
            if(ldt1.equals(ldt2)) return 0;
            else if(ldt1.isBefore(ldt2)) return -1; else return 1 ; 
       };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       List<String> lines = null;
       List<TransCaixa> ltc = new ArrayList<>();
       SortedSet<TransCaixa> transOrdData = new TreeSet<>(transPorData);
       
       try{
           lines = Files.readAllLines(Paths.get("transCaixa.txt"));
           for(String s: lines){
               ltc.add(strToTransCaixa(s));
           }
       }
       catch(IOException e){
           System.out.println("Erro: " + e.getMessage());
       }
       
       transOrdData.addAll(ltc);
       for(TransCaixa tc : transOrdData) 
           System.out.println(tc);
       
       System.out.println("TransCaixa: " + ltc.size());
    }
    
}
