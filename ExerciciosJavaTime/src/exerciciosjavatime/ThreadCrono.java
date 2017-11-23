package exerciciosjavatime;

/**
 *
 * @author Ricardo
 */
public class ThreadCrono extends Thread{
    private int h, m, s, mili;
    private long time, c;
    private boolean power;
    
    public ThreadCrono(){
        this.h = 0;        
        this.m = 0;
        this.s = 0;
        this.mili = 0;
        this.power = true;
        
        this.c = 0;
        this.time = 0;         
    }
    
    public synchronized void off(){
        power = false;
    }
    
    public synchronized void on(){
        power = true;
        notifyAll();
    }
    
    public synchronized void reset(){
        this.h = 0;        
        this.m = 0;
        this.s = 0;
        this.mili = 0;
    }
    
    @Override
    public void run(){
        while(true){
            System.out.println("ON");
            while(power){
                //long init = System.nanoTime();
                if(mili%7 == 0){
                    clearScreen();
                    System.out.println(h + " : " + m + " : " + s +", " + mili);
                }
                updateTime();
                sleep();
                //long end = System.nanoTime();
                //calc(init, end);
            }
            System.out.println("OFF - " + ((time/1e9d)/c) );
            
            waitOff();            
        }
    }
    private void calc(long init, long end){
        long tmpTime = end - init;
        
        time += tmpTime;
        c++;
    }
    private void updateTime(){
        mili++;
        if(mili == 100){
            mili = 0;
            s++;
            if (s == 59){
                s = 0;
                m++;
                if (m == 59){
                    m = 0;
                    h++;
                    if (h == 23)
                        h = 0; 
                }
            }
        }
    }
    private synchronized void waitOff(){
        try {
            wait();
        } catch (InterruptedException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    private void sleep(){
        try {
            Thread.sleep(9);
        } catch (InterruptedException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
    private void clearScreen() {
       for(int i = 0; i<20; i++){
           System.out.println();
       } 
    }
}
