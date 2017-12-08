/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calendar.Interfaces;

import javax.swing.JTextField;

/**
 *
 * @author Ricardo
 */
public interface Chronometer {
    public void off();
    public void on(JTextField h, JTextField m, JTextField s, JTextField mili);
    public void reset();
}
