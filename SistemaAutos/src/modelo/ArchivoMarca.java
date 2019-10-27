/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Chris
 */
public class ArchivoMarca {

    private String codMarca;
    private String nomMarca;
    private String estMarca;
    private String obsMarca;

    public void LeerPlantilla() {//Metodo para Cerrar
        try (Scanner datEntrada=new Scanner(new File("C:\\Users\\Chris\\Documentos\\Java-Projects\\SistemaAutos\\src\\LoadMarcas.txt"))){
            while (datEntrada.hasNextLine()) {
                codMarca="";
                String linea;
                linea = datEntrada.nextLine();
                
                try (Scanner datArchivo=new Scanner (linea)){
                    while (!datArchivo.hasNextInt()) {
                        codMarca += datArchivo.next()+" ";                        
                    }                    
                    codMarca=codMarca.trim();
                    
                    if (datEntrada.hasNextLine()) {
                        nomMarca += datArchivo.next()+" ";
                    }                    
                    nomMarca=nomMarca.trim();
                    
                    if (datEntrada.hasNextLine()) {
                        estMarca += datArchivo.next()+" ";
                    }                    
                    estMarca=estMarca.trim();
                    
                    if (datEntrada.hasNextLine()) {
                        obsMarca += datArchivo.next();
                    }                    
                    obsMarca=obsMarca.trim();
                }
                //Ver datos
                System.out.println(codMarca+";"+nomMarca+";"+estMarca+";"+obsMarca);
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

}
