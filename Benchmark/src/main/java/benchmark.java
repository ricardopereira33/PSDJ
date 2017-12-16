import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Crono;
import Utils.Printer;
import Utils.TestUtils;
import Utils.Tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractMap;
import java.util.ArrayList;
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

    private static void processFile(TestUtils t, Printer p, BufferedReader br) throws IOException {
        String line = null;

        while(true) {
            p.printMenu();
            line = br.readLine();
            switch (line) {
                case "0":
                    return;
                case "1":
                    t.test1();
                    pressToContinue(br);
                    break;
                case "2":
                    t.test2();
                    pressToContinue(br);
                    break;
                case "3":
                    t.test3();
                    pressToContinue(br);
                    break;
                case "4":
                    t.test4();
                    pressToContinue(br);
                    break;
                case "5":
                    t.test5();
                    pressToContinue(br);
                    break;
                case "6":
                    t.test6();
                    pressToContinue(br);
                    break;
                case "7":
                    t.test7();
                    pressToContinue(br);
                    break;
                case "8":
                    t.test8();
                    pressToContinue(br);
                    break;
                case "9":
                    t.test9();
                    pressToContinue(br);
                    break;
                case "10":
                    t.test10();
                    pressToContinue(br);
                    break;
                case "11":
                    t.test11();
                    pressToContinue(br);
                    break;
                case "12":
                    t.test12();
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

        TestUtils t = new TestUtils(ltc, tools);
        processFile(t, p, br);
    }
}

