/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import logica.Controlador;
import vista.frmPrincipal;

/**
 *
 * @author Chris
 */
public class CorrelacionAPP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        frmPrincipal AppfrmGUI = new frmPrincipal();//Instancio y creo un nuevo objeto
        //Instancio y creo un nuevo objeto y paso el form como parametro
        Controlador AppControlador = new Controlador(AppfrmGUI);
        AppControlador.iniciarGUI();
        AppfrmGUI.setVisible(true);
    }
    
}
