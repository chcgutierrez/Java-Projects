/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programa;

import modelo.*;
import vista.*;
import controlador.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        OrdenBD AppOrdenBD = new OrdenBD();//Instancio y creo un nuevo objeto
        OrdenDAO AppOrdenDAO = new OrdenDAO();//Instancio y creo un nuevo objeto
        frmOrdenTra AppfrmOrden = new frmOrdenTra();//Instancio y creo un nuevo objeto

        ClienteBD AppClienteBD = new ClienteBD();//Instancio y creo un nuevo objeto
        ClienteDAO AppClienteDAO = new ClienteDAO();//Instancio y creo un nuevo objeto
        
        CarroBD AppCarroBD = new CarroBD();//Instancio y creo un nuevo objeto
        CarroDAO AppCarroDAO = new CarroDAO();//Instancio y creo un nuevo objeto

        RepuestoBD AppRepuestoBD = new RepuestoBD();//Instancio y creo un nuevo objeto
        RepuestoDAO AppRepuestoDAO = new RepuestoDAO();//Instancio y creo un nuevo objeto
        
        EmpleadoBD AppEmpleBD = new EmpleadoBD();//Instancio y creo un nuevo objeto
        EmpleadoDAO AppEmpleDAO = new EmpleadoDAO();//Instancio y creo un nuevo objeto

        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
        ControlOrdenTra AppControlOrden = new ControlOrdenTra(AppCarroBD, AppCarroDAO, AppEmpleBD, AppEmpleDAO,
                                                              AppRepuestoBD, AppRepuestoDAO, AppOrdenBD, AppOrdenDAO, AppfrmOrden);
        try {
            AppControlOrden.IniciarOrden();//Invoco el metodo para el form
        } catch (ParseException ex) {
            Logger.getLogger(AppSysAutos.class.getName()).log(Level.SEVERE, null, ex);
        }
        AppfrmOrden.setVisible(true);//Invoco el metodo

        // TODO code application logic here
//        CarroBD AppCarroBD = new CarroBD();//Instancio y creo un nuevo objeto
//        CarroDAO AppCarroDAO = new CarroDAO();//Instancio y creo un nuevo objeto
//        frmVehiculo AppfrmVehiculo = new frmVehiculo();//Instancio y creo un nuevo objeto
//
//        ClienteBD AppClienteBD = new ClienteBD();//Instancio y creo un nuevo objeto
//        ClienteDAO AppClienteDAO = new ClienteDAO();//Instancio y creo un nuevo objeto        
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorCarro AppControlCarro = new ControladorCarro(AppCarroBD, AppCarroDAO, AppfrmVehiculo, AppClienteBD, AppClienteDAO);
//        try {
//            AppControlCarro.IniciarCarro();//Invoco el metodo para el form
//        } catch (ParseException ex) {
//            Logger.getLogger(AppSysAutos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        AppfrmVehiculo.setVisible(true);//Invoco el metodo

//        // TODO code application logic here
//        EmpleadoBD AppEmpleBD = new EmpleadoBD();//Instancio y creo un nuevo objeto
//        EmpleadoDAO AppEmpleDAO = new EmpleadoDAO();//Instancio y creo un nuevo objeto
//        frmMecanico AppfrmEmple = new frmMecanico();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorEmpleado AppControlEmple = new ControladorEmpleado(AppEmpleBD, AppEmpleDAO, AppfrmEmple);
//        AppControlEmple.IniciarEmple();//Invoco el metodo para el form
//        AppfrmEmple.setVisible(true);//Invoco el metodo
//        // TODO code application logic here
//        ClienteBD AppClienteBD = new ClienteBD();//Instancio y creo un nuevo objeto
//        ClienteDAO AppClienteDAO = new ClienteDAO();//Instancio y creo un nuevo objeto
//        frmCliente AppfrmCliente = new frmCliente();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorCliente AppControlCliente = new ControladorCliente(AppClienteBD, AppClienteDAO, AppfrmCliente);
//        try {
//            AppControlCliente.IniciarCliente();//Invoco el metodo para el form
//        } catch (ParseException ex) {
//            Logger.getLogger(AppSysAutos.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        AppfrmCliente.setVisible(true);//Invoco el metodo

//        // TODO code application logic here
//        TipoDocBD AppTipoDocBD = new TipoDocBD();//Instancio y creo un nuevo objeto
//        TipoDocDAO AppTipoDocDAO = new TipoDocDAO();//Instancio y creo un nuevo objeto
//        frmTipoDoc AppfrmTipoDoc = new frmTipoDoc();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorTipoDoc AppControlTipoDoc = new ControladorTipoDoc(AppTipoDocBD, AppTipoDocDAO, AppfrmTipoDoc);
//        AppControlTipoDoc.IniciarTipoDoc();//Invoco el metodo para el form
//        AppfrmTipoDoc.setVisible(true);//Invoco el metodo

//        // TODO code application logic here
//        RepuestoBD AppRepuestoBD = new RepuestoBD();//Instancio y creo un nuevo objeto
//        RepuestoDAO AppRepuestoDAO = new RepuestoDAO();//Instancio y creo un nuevo objeto
//        frmRepuesto AppfrmRepuesto = new frmRepuesto();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorRepuesto AppControlRepu = new ControladorRepuesto(AppRepuestoBD, AppRepuestoDAO, AppfrmRepuesto);
//        AppControlRepu.IniciarRepuesto();//Invoco el metodo para el form
//        AppfrmRepuesto.setVisible(true);//Invoco el metodo

//        // TODO code application logic here
//        TipoBD AppTipoBD = new TipoBD();//Instancio y creo un nuevo objeto
//        TipoDAO AppTipoDAO = new TipoDAO();//Instancio y creo un nuevo objeto
//        frmTipo AppfrmTipo = new frmTipo();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorTipo AppControlTipo = new ControladorTipo(AppTipoBD, AppTipoDAO, AppfrmTipo);
//        AppControlTipo.IniciarTipo();//Invoco el metodo para el form
//        AppfrmTipo.setVisible(true);//Invoco el metodo

//        // TODO code application logic here
//        CiudadBD AppCiudadBD = new CiudadBD();//Instancio y creo un nuevo objeto
//        CiudadDAO AppCiudadDAO = new CiudadDAO();//Instancio y creo un nuevo objeto
//        frmCiudad AppfrmCiudad = new frmCiudad();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorCiudad AppControlCiudad = new ControladorCiudad(AppCiudadBD, AppCiudadDAO, AppfrmCiudad);
//        AppControlCiudad.IniciarCiudad();//Invoco el metodo para el form
//        AppfrmCiudad.setVisible(true);//Invoco el metodo

//        // TODO code application logic here
//        ColorBD AppColorBD = new ColorBD();//Instancio y creo un nuevo objeto
//        ColorDAO AppColorDAO = new ColorDAO();//Instancio y creo un nuevo objeto
//        frmColor AppfrmColor = new frmColor();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorColor AppControlColor = new ControladorColor(AppColorBD, AppColorDAO, AppfrmColor);
//        AppControlColor.IniciarColor();//Invoco el metodo para el form
//        AppfrmColor.setVisible(true);//Invoco el metodo

//        // TODO code application logic here
//        MarcaEnt AppMarcaEnt = new MarcaEnt();//Instancio y creo un nuevo objeto
//        MarcaDAO AppMarcaDAO = new MarcaDAO();//Instancio y creo un nuevo objeto
//        frmMarca AppfrmMarca = new frmMarca();//Instancio y creo un nuevo objeto
//        //Instancio y creo un nuevo objeto y paso los anteriores como parametro
//        ControladorMarca AppControladorMarca = new ControladorMarca(AppMarcaEnt, AppMarcaDAO, AppfrmMarca);
//        AppControladorMarca.IniciarMarca();//Invoco el metodo para el form
//        AppfrmMarca.setVisible(true);//Invoco el metodo
    }

}
