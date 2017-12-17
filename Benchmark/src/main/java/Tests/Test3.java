package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class Test3 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test3(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        // int[]

        // IntStream

        // List<Integer>
    }
}
