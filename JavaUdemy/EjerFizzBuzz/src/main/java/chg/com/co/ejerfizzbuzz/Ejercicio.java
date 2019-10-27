/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chg.com.co.ejerfizzbuzz;

/**
 *
 * @author Chris
 */
public class Ejercicio {

    public static void main(String[] args) {
        // Cuenta de 1 a 100
        for (int n = 1; n <= 50; n++) {
            String imprimir = "";
            if (n % 3 == 0) { //Si n es divisible solo x 3
                imprimir += "Fizz";
            }
            if (n % 5 == 0) { //Si n es divisible solo x 5
                imprimir += "Buzz";
            }
            //Si n es divisible x 3 o x 5 o ambos
            if (n % 3 == 0 || n % 5 == 0) {
                System.out.println(imprimir);
            } else { //Caso contrario, muestra el numero
                System.out.println(n);
            }
        }
    }
}
