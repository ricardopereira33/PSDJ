package calendar.Interfaces;

import javax.swing.JTextField;

/**
 *
 * @author Ricardo
 */
public interface Chronometer {
    
    // Desligar o cronómetro.
    public void off();
    
    // Ligar o cronómetro.
    public void on();
    
    // Reiniciar o cronómetro.
    public void reset();
    
    // Sair do cronómetro.
    public void exit();
    
    // Atualizar as componentes de texto de cada parâmetro do cronómetro.
    public void setComponents(JTextField hour, JTextField minute, JTextField second, JTextField milisecond);
}
