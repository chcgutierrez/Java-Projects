/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import modelo.*;
import vista.*;
import controlador.*;

/**
 *
 * @author Chris
 */
public class AppSysAutos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MarcaEnt AppMarcaEnt = new MarcaEnt();//Instancio y creo un nuevo objeto
        MarcaDAO AppMarcaDAO = new MarcaDAO();//Instancio y creo un nuevo objeto
        frmMarca AppfrmMarca = new frmMarca();//Instancio y creo un nuevo objeto
        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
        ControladorMarca AppControladorMarca = new ControladorMarca(AppMarcaEnt, AppMarcaDAO, AppfrmMarca);
        AppControladorMarca.IniciarMarca();//Invoco el metodo para el form
        AppfrmMarca.setVisible(true);//Invoco el metodo

    }

}
