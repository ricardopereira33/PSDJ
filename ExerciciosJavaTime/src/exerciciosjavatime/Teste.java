/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciosjavatime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ricardo
 */
public class Teste {
     
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        Util_Datas ejt = new Util_Datas();
        
        while( (line = br.readLine()) != null){
            switch(line){
                case "1": ejt.ex1();
                          break;
                case "2": ejt.ex2();
                          break;
                case "3": ejt.ex3();
                          break;
                case "4": ejt.ex4();
                          break;
                case "5": ejt.ex5();
                          break;
                case "6": ejt.ex6();
                          break;
                case "7": ejt.ex7();
                          break;
                case "8": ejt.ex8();
                          break;
                case "9": ejt.ex9();
                          break;
                case "10": ejt.ex10();
                           break;
                case "11": ejt.ex11();
                           break;
                case "12": ejt.ex12();
                           break;
                case "13": ejt.ex13();
                           break;
                case "14": ejt.ex14();
                           break;
                case "15": ejt.ex15();
                           break;
                case "16": ejt.ex16();
                           break;
                case "17": ejt.ex17();
                           break;
                case "18": ejt.ex18();
                           break;
                case "19": ejt.ex19();
                           break;
                case "20": ejt.ex20();
                           break;
                case "21": ejt.ex21();
                           break;
                case "22": ejt.ex22();
                           break;
                case "23": ejt.ex23();
                           break;
                case "24": ejt.ex24();
                           break;
                case "25": ejt.ex25();
                           break;
                case "26": ejt.ex26();
                           break;
                case "27": ejt.ex27();
                           break;
                case "28": ejt.ex28();
                           break;
                case "29": ejt.ex29();
                           break;
                case "30": ejt.ex30();
                           break;
                default : System.out.println("Comando errado");
                          break;
            }
        }
    }
}
