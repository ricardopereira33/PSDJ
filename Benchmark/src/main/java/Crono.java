/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ricardo
 */

public class Crono {

  private static long inicio = 0L;
  private static long fim = 0L;
  
/** inicia a contagem de tempo */
  public static void start() { 
      fim = 0L;
      inicio = System.nanoTime();
  }
  
/** termina a contagem de tempo  e devolve diferença em segs */
  public static double stop() { 
      fim = System.nanoTime();
      long elapsedTime = fim - inicio;
      double segs = elapsedTime / 1.0E09;
      return segs;
  }
  
/** converte a diferença numa string */
  public static String print() {
      return "" + stop();
  }
}
