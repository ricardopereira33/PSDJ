import Interfaces.Test;
import Utils.Printer;
import Utils.TestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Benchmark {
    public static void main (String args[]) throws IOException {
        Test t = new TestUtils();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        Printer p = new Printer();

        while(true){
            p.printMenu();
            line = br.readLine();
            switch(line) {
                case "0": return;
                case "1": t.test1();
                    break;
                case "2": t.test2();
                    break;
                case "3": t.test3();
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    break;
                case "8":
                    break;
                case "9":
                    break;
                case "10":
                    break;
                case "11":
                    break;
                case "12":
                    break;
                default:
                    System.out.println("Comando inválido");
            }
        }
    }
}

