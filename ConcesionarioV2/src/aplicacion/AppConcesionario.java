package aplicacion;

import controlador.*;
import modelo.*;
import vista.*;

public class AppConcesionario {

    public static void main(String[] args) {
        frmPrincipal frmIniciarTodos = null;
        
        ControlPrincipal appControl=new ControlPrincipal(frmIniciarTodos);
        appControl.CargarPrincipal();
        //frmPrincipal appPrincipal=new frmPrincipal();
        //frmIniciarTodos.setExtendedState(6);
        //frmIniciarTodos.setTitle("Motor & Cars");
       // frmIniciarTodos.setVisible(true);

    }

}
