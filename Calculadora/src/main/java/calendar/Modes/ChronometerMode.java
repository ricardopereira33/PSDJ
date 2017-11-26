/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import calendar.Interfaces.Chronometer;

/**
 *
 * @author Ricardo
 */
public class ChronometerMode extends Thread implements Chronometer {
    private int h, m, s;
    private long mili;
    private boolean power;
    
    public ChronometerMode(){
        this.h = 0;        
        this.m = 0;
        this.s = 0;
        this.mili = 0;
        this.power = true;        
    }
    
    @Override
    public synchronized void off(){
        power = false;
    }
    
    @Override
    public synchronized void on(){
        power = true;
        notifyAll();
    }
    
    @Override
    public synchronized void reset(){
        this.h = 0;        
        this.m = 0;
        this.s = 0;
        this.mili = 0;
    }
    
    @Override
    public void run(){
        long init, end;
        while(true){
            System.out.println("ON");
            while(power){
                init = System.nanoTime();
                if(mili%7 == 0){
                    clearScreen();
                    System.out.println(h + " : " + m + " : " + s +", " + mili);
                }
                sleep();
                end = System.nanoTime();
                updateTime((long) ((end-init)/1e6d));
                mili+=(long) (((System.nanoTime()-end)+5e5d)/1e6d);
            }
            System.out.println("OFF");
            
            waitOff();            
        }
    }

    private void updateTime(long time){
        mili+=time;
        if(mili >= 1000){
            mili = mili%1000;
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
    
    /** Example **/
    /*
    public void crono() throws IOException{
        boolean power = true;
        boolean cronoOn = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        ThreadCrono tc = new ThreadCrono();
        
        while(power){
            line = br.readLine();
            switch(line){
                case "start" :  if(!cronoOn) { 
                                    tc.start(); 
                                    cronoOn = true;
                                }
                                else tc.on();
                                break;
                case "stop" :   tc.off();
                                break;
                case "reset":   tc.reset();
                                break;
            }
        }
    }
    */
}
