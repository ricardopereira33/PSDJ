/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exerciciosjavatime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

/**
 *
 * @author Ricardo
 */
public class Teste {
     
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        System.out.println("Start..");
        while( (line = br.readLine()) != null){
            switch(line){
                case "1": System.out.println(""+LocalDateTime.now().query(Util_Datas::LocalDateTimeToInstant));
                          break;
                case "2": Util_Datas.ex2();
                          break;
                case "3": Util_Datas.ex3();
                          break;
                case "4": Util_Datas.ex4();
                          break;
                case "5": Util_Datas.ex5();
                          break;
                case "6": Util_Datas.ex6();
                          break;
                case "7": Util_Datas.ex7();
                          break;
                case "8": Util_Datas.ex8();
                          break;
                case "9": Util_Datas.ex9();
                          break;
                case "10": Util_Datas.ex10();
                           break;
                case "11": Util_Datas.ex11();
                           break;
                case "12": Util_Datas.ex12();
                           break;
                case "13": Util_Datas.ex13();
                           break;
                case "14": Util_Datas.ex14();
                           break;
                case "15": Util_Datas.ex15();
                           break;
                case "16": Util_Datas.ex16();
                           break;
                case "17": Util_Datas.ex17();
                           break;
                case "18": Util_Datas.ex18();
                           break;
                case "19": Util_Datas.ex19();
                           break;
                case "20": Util_Datas.ex20();
                           break;
                case "21": Util_Datas.ex21();
                           break;
                case "22": Util_Datas.ex22();
                           break;
                case "23": Util_Datas.ex23();
                           break;
                case "24": Util_Datas.ex24();
                           break;
                case "25": Util_Datas.ex25();
                           break;
                case "26": Util_Datas.ex26();
                           break;
                case "27": Util_Datas.ex27();
                           break;
                case "28": Util_Datas.ex28();
                           break;
                case "29": Util_Datas.ex29();
                           break;
                case "30": Util_Datas.ex30();
                           break;
                case "crono": Util_Datas.crono();
                              break;
                default : System.out.println("Comando errado");
                          break;
            }
        }
    }
}
