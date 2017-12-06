/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import calendar.Interfaces.Chronometer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Ricardo
 */
public class ChronometerMode extends Thread implements Chronometer {
    private int hour, min, sec;
    private long mili;
    private boolean power;
    
    public ChronometerMode(){
        this.hour = 0;
        this.min = 0;
        this.sec = 0;
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
        this.hour = 0;
        this.min = 0;
        this.sec = 0;
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
                    System.out.println(hour + " : " + min + " : " + sec +", " + mili);
                }
                sleep();
                end = System.nanoTime();
                updateTime((long) ((end-init+5e5d)/1e6d));
            }
            System.out.println("OFF");
            waitOff();            
        }
    }

    private void updateTime(long time){
        mili+=time;
        if(mili >= 1000){
            mili = mili%1000;
            sec++;
            if (sec == 59){
                sec = 0;
                min++;
                if (min == 59){
                    min = 0;
                    hour++;
                    if (hour == 23)
                        hour = 0;
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
       for(int i = 0; i<10; i++){
           System.out.println();
       } 
    }
    
    /** Example **/

    public static void main(String[] args) throws IOException {
        boolean power = true;
        boolean cronoOn = false;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        System.out.println("start");
        ChronometerMode tc = new ChronometerMode();
        
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
            System.out.println("cmd: "+line);
        }
    }
}
