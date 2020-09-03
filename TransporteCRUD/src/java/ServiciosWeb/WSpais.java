/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServiciosWeb;

import Modelo.Pais;
import Modelo.PaisDAO;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 *
 * @author Chris
 */
@WebService(serviceName = "WSpais")
public class WSpais {

    PaisDAO oPaisDAO = new PaisDAO(); //Instancio la clase Pais para crear un nuevo objeto

    @WebMethod(operationName = "Buscar")
    public List<Pais> Buscar() {
        List datosPais = oPaisDAO.BuscarPais(); //lleno el obj. List con el objeto y el método
        return datosPais;
    }
}
