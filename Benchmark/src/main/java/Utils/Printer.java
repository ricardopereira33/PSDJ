package Utils;

import static java.lang.System.out;

public class Printer {
    public void printMenu(){
        clear();
        out.println("========= MENU ==========");
        out.println("1.\tTeste 1");
        out.println("2.\tTeste 2");
        out.println("3.\tTeste 3");
        out.println("4.\tTeste 4");
        out.println("5.\tTeste 5");
        out.println("6.\tTeste 6");
        out.println("7.\tTeste 7");
        out.println("8.\tTeste 8");
        out.println("9.\tTeste 9");
        out.println("10.\tTeste 10");
        out.println("11.\tTeste 11");
        out.println("12.\tTeste 12");
        out.print("Opção: ");
    }

    public void printFileChoose() {
        clear();
        out.println("========= FILE ==========");
        out.println("1. 1M");
        out.println("2. 2M");
        out.println("3. 4M");
        out.println("4. 8M");
        out.print("Opção: ");
    }

    private void clear(){
        for(int i = 0; i<100; i++)
            out.println();
    }
}
