package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

public class Test3 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test3(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        List<Integer> list = creatRandomData();
        int[] array = getArray(list);

        //int []
        System.out.println("# int[]");
        Supplier<int[]> sup1 = () -> removeRep(array);
        SimpleEntry<Double, int[]> res1 = t.testeBoxGenW(sup1);
        System.out.println("Time: "+ res1.getKey() +"\t Res: " + res1.getValue().length);

        //IntStream
        System.out.println("# IntStream");
        Supplier<IntStream> sup2 = () -> list.stream().mapToInt(i -> i).distinct();
        SimpleEntry<Double, IntStream> res2 = t.testeBoxGenW(sup2);
        System.out.println("Time: "+ res2.getKey() +"\t Res: " + res2.getValue().count());

        //List<Integer>
        System.out.println("# List<Integer>");
        Supplier<List<Integer>> sup3 = () -> list.stream().distinct().collect(toList());
        SimpleEntry<Double, List<Integer>> res3 = t.testeBoxGenW(sup3);
        System.out.println("Time: "+ res3.getKey() +"\t Res: " + res3.getValue().size());
    }

    private int[] getArray(List<Integer> l) {
        int[] list = new int[ltc.size()];
        int i = 0;
        for(int value : l){
            list[i] = value;
            i++;
        }
        return list;
    }

    private List<Integer> creatRandomData() {
        List<Integer> list = new ArrayList<>(ltc.size());
        Random rand = new Random();
        for(int i = 0; i<ltc.size(); i++){
            list.add(rand.nextInt(9999) + 1);
        }
        return list;
    }

    private int[] removeRep(int[] array) {
        int[] res = new int[array.length];
        int i = 0;
        for(int j = 0; j < array.length; j++){
            if(!contains(res, array[j]))
                res[i] = array[j];
        }
        return res;
    }

    private boolean contains(int[] res, int i) {
        for (int re : res) {
            if(re == 0)
                break;
            if(re == i)
                return true;
        }
        return false;
    }
}
