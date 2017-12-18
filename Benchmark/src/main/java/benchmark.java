import Interfaces.Test;
import Structure.TransCaixa;
import Tests.*;
import Utils.Crono;
import Utils.Printer;
import Utils.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.System.out;

public class Benchmark {

    private static String chooseFile(BufferedReader br, Printer p) throws IOException {
        String file = "transCaixa";
        String input = null;
        boolean valid = true;

        while(valid) {
            p.printFileChoose();
            input = br.readLine();
            switch(input){
                case "1":
                    return file + "1M.txt";
                case "2":
                    return file + "2M.txt";
                case "4":
                    return file + "4M.txt";
                case "8":
                    return file + "8M.txt";
                default:
                    System.out.println("Comando inválido");
            }
        }
        return null;
    }

    private static List<TransCaixa> loadFile(String fileName, Tools t, BufferedReader br) throws IOException {
        Crono.start();
        List<TransCaixa> res = t.setupStream(fileName);
        out.println("Setup com Streams: " + Crono.stop()*1000 + " ms");
        out.println("Transacções lidas: " + res.size());
        t.memoryUsage();

        pressToContinue(br);

        return res;
    }

    private static void processFile(List<TransCaixa> ltc, Tools tools, Printer p, BufferedReader br) throws IOException {
        String line = null;

        while(true) {
            p.printMenu();
            line = br.readLine();
            switch (line) {
                case "0":
                    return;
                case "1":
                    Test t1 = new Test1(ltc,tools);
                    t1.exe();
                    pressToContinue(br);
                    break;
                case "2":
                    Test t2 = new Test2(ltc,tools);
                    t2.exe();
                    pressToContinue(br);
                    break;
                case "3":
                    Test t3 = new Test3(ltc,tools);
                    t3.exe();
                    pressToContinue(br);
                    break;
                case "4":
                    Test t4 = new Test4(ltc,tools);
                    t4.exe();
                    pressToContinue(br);
                    break;
                case "5":
                    Test t5 = new Test5(ltc,tools);
                    t5.exe();
                    pressToContinue(br);
                    break;
                case "6":
                    Test t6 = new Test6(ltc,tools);
                    t6.exe();
                    pressToContinue(br);
                    break;
                case "7":
                    Test t7 = new Test7(ltc,tools);
                    t7.exe();
                    pressToContinue(br);
                    break;
                case "8":
                    Test t8 = new Test8(ltc,tools);
                    t8.exe();
                    pressToContinue(br);
                    break;
                case "9":
                    Test t9 = new Test9(ltc,tools);
                    t9.exe();
                    pressToContinue(br);
                    break;
                case "10":
                    Test t10 = new Test10(ltc,tools);
                    t10.exe();
                    pressToContinue(br);
                    break;
                case "11":
                    Test t11 = new Test11(ltc,tools);
                    t11.exe();
                    pressToContinue(br);
                    break;
                case "12":
                    Test t12 = new Test12(ltc,tools);
                    t12.exe();
                    pressToContinue(br);
                    break;
                default:
                    System.out.println("Comando inválido");
                    pressToContinue(br);
            }
        }
    }

    private static void pressToContinue(BufferedReader br) throws IOException {
        System.out.println("\t>Press Enter\n");
        br.readLine();
    }

    public static void main (String args[]) throws IOException {
        Printer p = new Printer();
        Tools tools = new Tools();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String fileName = chooseFile(br, p);
        List<TransCaixa> ltc = loadFile(fileName, tools, br);

        processFile(ltc, tools, p, br);
    }
}

