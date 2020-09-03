/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algoeuclides;

/**
 *
 * @author Chris
 */
public class AlgoEuclides {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("¿Cuál es el mcd de 4 y 12? Es "+obtener_mcd(4,12));
        System.out.println("¿Cuál es el mcd de 8 y 12? Es "+obtener_mcd(8,12));
        System.out.println("¿Cuál es el mcd de 48 y 60? Es "+obtener_mcd(48,60));
        System.out.println("¿Cuál es el mcd de 96 y 36? Es "+obtener_mcd(96,36));
    }
    
    static int obtener_mcd(int a, int b) {
       if(b==0)
           return a;
       else
           return obtener_mcd(b, a % b);
   }
    
}
