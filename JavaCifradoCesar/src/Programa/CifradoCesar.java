
package Programa;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class CifradoCesar {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String texto;
        int codigo;
        char opcion;

        //Captura el texto para cifrar
        do {
            System.out.print("Introduce un texto: ");
            texto = sc.nextLine();
        } while (texto.isEmpty());

        //Valor inicial para el desplazamiento
        do {
            System.out.print("Introduce el código: ");
            codigo = sc.nextInt();
        } while (codigo < 1);

        //Ingresar operacion: cifrar o descifrar
        do {
            sc.nextLine();
            System.out.print("(C) cifrar o (D) descifrar?: ");
            opcion = (char) System.in.read();
        } while (Character.toUpperCase(opcion) != 'C' && Character.toUpperCase(opcion) != 'D');
        if (Character.toUpperCase(opcion) == 'C') {
            System.out.println("Texto con cifrado: " + GenerarCifrado(texto, codigo));
        } else {
            System.out.println("Texto sin cifrado: " + QuitarCifrado(texto, codigo));
        }
    }

    //Método para cifrar el texto
    public static String GenerarCifrado(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26; //Módulo de cifrado
        for (int i = 0; i < texto.length(); i++) { //Recorre la longitud del texto ingresado
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) + codigo) > 'z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) + codigo) > 'Z') {
                    cifrado.append((char) (texto.charAt(i) + codigo - 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) + codigo));
                }
            }
        }
        return cifrado.toString(); //Retorna el texto cifrado
    }

    //Método para descifrar el texto
    public static String QuitarCifrado(String texto, int codigo) {
        StringBuilder cifrado = new StringBuilder();
        codigo = codigo % 26; //Módulo de cifrado
        for (int i = 0; i < texto.length(); i++) { //Recorre la longitud del texto ingresado
            if (texto.charAt(i) >= 'a' && texto.charAt(i) <= 'z') {
                if ((texto.charAt(i) - codigo) < 'a') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            } else if (texto.charAt(i) >= 'A' && texto.charAt(i) <= 'Z') {
                if ((texto.charAt(i) - codigo) < 'A') {
                    cifrado.append((char) (texto.charAt(i) - codigo + 26));
                } else {
                    cifrado.append((char) (texto.charAt(i) - codigo));
                }
            }
        }
        return cifrado.toString();
    }

}
