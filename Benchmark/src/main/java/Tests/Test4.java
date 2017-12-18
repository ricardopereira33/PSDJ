package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.function.Supplier;

public class Test4 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test4(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        BiFunction<Integer, Integer, Integer> division =  (i1, i2) -> i1/i2;
        int[] list = creatRandomData();

        /*** Sequencial ***/
        // lambda
        Supplier<Integer> sup1 = () -> Arrays.stream(list).reduce(0, (i1, i2) -> i1/i2);
        SimpleEntry<Double, Integer> res1 = t.testeBoxGenW(sup1);
        System.out.println("Time: "+ res1.getKey() +"\t Res: " + res1.getValue());

        // Bifun
        Supplier<Integer> sup2 = () -> Arrays.stream(list).reduce(0, (i1,i2) -> division.apply(i1,i2));
        SimpleEntry<Double, Integer> res2 = t.testeBoxGenW(sup2);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue());

        // metodo
        Supplier<Integer> sup3 = () -> Arrays.stream(list).reduce(0, (i1,i2) -> div(i1,i2));
        SimpleEntry<Double, Integer> res3 = t.testeBoxGenW(sup3);
        System.out.println("Time: "+ res3.getKey() +"\t Res: " + res3.getValue());

        /*** Parallel ***/


    }

    private int[] creatRandomData() {
        int[] list = new int[ltc.size()];
        Random rand = new Random();
        for(int i = 0; i<ltc.size(); i++){
            list[i] = rand.nextInt(9999) + 1;
        }
        return list;
    }

    private static int div(int i1, int i2){
        return i1/i2;
    }
}
