package calendar.Modes;

import calendar.Interfaces.Chronometer;
import javax.swing.JTextField;

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
    public void setComponents(JTextField hour, JTextField minute, JTextField second, JTextField milisecond) {
        this.h = hour;
        this.m = minute;
        this.s = second;
        this.mil = milisecond;
    }
    
    @Override
    public synchronized void off(){
        power = false;
    }
    
    @Override
    public synchronized void on(){
        if(!chronoOn) { 
            this.start(); 
            chronoOn = true;
        }
        else{ 
            power = true;
            notifyAll();
        }
    }
    
    @Override
    public synchronized void reset(){
        power = false;
        hour = 0;
        min = 0;
        sec = 0;
        mili = 0;
        updateTextField(); 
    }
    
    @Override
    public synchronized void exit(){
        reset();
        power = false;
    }
    
    @Override
    public void run(){
        long initTime, endTime;
        while(true){
            while(power){
                initTime = System.nanoTime();
                sleep();
                endTime = System.nanoTime();
                if(power) updateTime((long) ((endTime-initTime+5e5d)/1e6d));
            }
            waitOff();   
        }
    }
    
    private void updateTextField() {
        h.setText("0"+hour);
        m.setText("0"+min);
        s.setText("0"+sec);
        mil.setText("0"+mili);
    }

    private void updateTime(long time){
        mili+=time;
        mil.setText(String.valueOf(mili));
        if(mili >= 1000){
            mili = mili%1000;
            sec++;
            s.setText(String.valueOf(sec));
            if (sec == 59){
                sec = 0;
                min++;
                m.setText(String.valueOf(min));
                if (min == 59){
                    min = 0;
                    hour++;
                    h.setText(String.valueOf(hour));
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
