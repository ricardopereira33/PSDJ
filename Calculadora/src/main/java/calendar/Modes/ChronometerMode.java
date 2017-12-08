/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Modes;

import calendar.Interfaces.Chronometer;
import javax.swing.JTextField;


/**
 *
 * @author Ricardo
 */
public class ChronometerMode extends Thread implements Chronometer {
    private int hour, min, sec;
    private long mili;
    private boolean power,chronoOn;
    private JTextField h, m, s, mil;
    
    public ChronometerMode(){
        this.hour = 0;
        this.min = 0;
        this.sec = 0;
        this.mili = 0;
        this.power = true;
        this.chronoOn = false;
    }
    
    @Override
    public synchronized void off(){
        power = false;
    }
    
    @Override
    public synchronized void on(JTextField h, JTextField m, JTextField s, JTextField mil){
        if(!chronoOn) { 
            this.start(); 
            chronoOn = true;
        }
        else{ 
            power = true;
            notifyAll();
        }
        this.h = h;
        this.m = m;
        this.s = s;
        this.mil = mil;
    }
    
    @Override
    public synchronized void reset(){
        this.hour = 0;
        this.min = 0;
        this.sec = 0;
        this.mili = 0;
        updateTextField();
    }
    
    @Override
    public void run(){
        long init, end;
        while(true){
            while(power){
                init = System.nanoTime();
                sleep();
                end = System.nanoTime();
                updateTime((long) ((end-init+5e5d)/1e6d));
            }
            waitOff();            
        }
    }
    
    private void updateTextField() {
        this.h.setText(""+hour);
        this.m.setText(""+min);
        this.s.setText(""+sec);
        this.mil.setText(""+mili);
    }

    private void updateTime(long time){
        mili+=time;
        mil.setText(""+mili);
        if(mili >= 1000){
            mili = mili%1000;
            sec++;
            s.setText(""+sec);
            if (sec == 59){
                sec = 0;
                min++;
                m.setText(""+min);
                if (min == 59){
                    min = 0;
                    hour++;
                    h.setText(""+hour);
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
            Thread.sleep(10);
        } catch (InterruptedException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
