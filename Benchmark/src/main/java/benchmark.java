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
                case "0":
                    return;
                case "1":
                    t.test1();
                    break;
                case "2":
                    t.test2();
                    break;
                case "3":
                    t.test3();
                    break;
                case "4":
                    t.test4();
                    break;
                case "5":
                    t.test5();
                    break;
                case "6":
                    t.test6();
                    break;
                case "7":
                    t.test7();
                    break;
                case "8":
                    t.test8();
                    break;
                case "9":
                    t.test9();
                    break;
                case "10":
                    t.test10();
                    break;
                case "11":
                    t.test11();
                    break;
                case "12":
                    t.test12();
                    break;
                default:
                    System.out.println("Comando inválido");
            }
        }
    }
}

