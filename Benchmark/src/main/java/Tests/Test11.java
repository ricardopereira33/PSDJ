package Tests;

import Interfaces.Test;
import Structure.TransCaixa;
import Utils.Tools;

import java.util.ArrayList;
import java.util.List;

public class Test11 implements Test{
    private final Tools t;
    public List<TransCaixa> ltc;

    public Test11(List<TransCaixa> l, Tools t){
        this.ltc = l;
        this.t = t;
    }

    @Override
    public void exe(){
        //usar outro teste com java 8 e 9
    }
}
