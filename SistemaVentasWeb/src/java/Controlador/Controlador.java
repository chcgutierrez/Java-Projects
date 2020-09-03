package Controlador;

import Modelo.*;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    Empleado oEmpleado = new Empleado();
    EmpleadoDAO oEmpleadoDAO = new EmpleadoDAO();
    int idEmp;

    Cliente oCliente = new Cliente();
    ClienteDAO oClienteDAO = new ClienteDAO();
    int idCliente;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menu"); //Ventana - Menú
        String accion = request.getParameter("btnAccion");

        if (menu.equalsIgnoreCase("principal")) { //Menu Principal
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }

        //Formulario Producto
        if (menu.equalsIgnoreCase("Producto")) { //Menu Producto

            switch (accion) {

                case "Listar": //Trae todos los registros

                    break;

                case "Eliminar": //Borrar registro con criterio
                    break;

                case "Buscar": //Buscar registro con criterio
                    break;

                case "Modificar": //Editar registro con criterio
                    break;

                case "Guardar": //Agregar nuevo registro 
                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("Producto.jsp").forward(request, response);
        }

        //Formulario Empleado
        if (menu.equalsIgnoreCase("Empleado")) { //Menu Empleado

            switch (accion) {

                case "Listar": //Trae todos los registros

                    List empLista = oEmpleadoDAO.listar();
                    request.setAttribute("atrEmpleado", empLista);
                    break;

                case "Eliminar": //Borrar registro con criterio

                    idEmp = Integer.valueOf(request.getParameter("EmpID"));
                    oEmpleadoDAO.borrar(idEmp);
                    request.getRequestDispatcher("Controlador?menu=Empleado&btnAccion=Listar").forward(request, response);

                    break;

                case "Buscar": //Buscar registro con criterio
                    break;

                case "Modificar": //Editar registro con criterio

                    idEmp = Integer.valueOf(request.getParameter("EmpID"));
                    Empleado objEmpleado = oEmpleadoDAO.listarID(idEmp);
                    request.setAttribute("Empleado", objEmpleado);
                    request.getRequestDispatcher("Controlador?menu=Empleado&btnAccion=Listar").forward(request, response);

                    break;

                case "Registrar": //Agregar nuevo registro

                    String strDNI = request.getParameter("txtEmpDNI");
                    String strEmpNombre = request.getParameter("txtEmpNombre").toUpperCase();
                    String strEmpTelefono = request.getParameter("txtEmpTelefono");
                    String strEmpEstado = request.getParameter("txtEmpEstado");
                    String strEmpUsuario = request.getParameter("txtEmpUsuario").toUpperCase();

                    oEmpleado.setDni(strDNI);
                    oEmpleado.setEmpNombre(strEmpNombre);
                    oEmpleado.setEmpTelefono(strEmpTelefono);
                    oEmpleado.setEstado(strEmpEstado);
                    oEmpleado.setUser(strEmpUsuario);

                    oEmpleadoDAO.agregar(oEmpleado);

                    request.getRequestDispatcher("Controlador?menu=Empleado&btnAccion=Listar").forward(request, response);

                    break;

                case "Actualizar":

                    String strDNI1 = request.getParameter("txtEmpDNI");
                    String strEmpNombre1 = request.getParameter("txtEmpNombre").toUpperCase();
                    String strEmpTelefono1 = request.getParameter("txtEmpTelefono");
                    String strEmpEstado1 = request.getParameter("txtEmpEstado");
                    String strEmpUsuario1 = request.getParameter("txtEmpUsuario").toUpperCase();

                    oEmpleado.setDni(strDNI1);
                    oEmpleado.setEmpNombre(strEmpNombre1);
                    oEmpleado.setEmpTelefono(strEmpTelefono1);
                    oEmpleado.setEstado(strEmpEstado1);
                    oEmpleado.setUser(strEmpUsuario1);
                    oEmpleado.setId(idEmp);

                    oEmpleadoDAO.actualizar(oEmpleado);

                    request.getRequestDispatcher("Controlador?menu=Empleado&btnAccion=Listar").forward(request, response);

                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("Empleado.jsp").forward(request, response);
        }

        //Formulario Cliente
        if (menu.equalsIgnoreCase("Cliente")) { //Menu Cliente

            switch (accion) {

                case "Listar": //Trae todos los registros

                    List cliLista = oClienteDAO.listar();
                    request.setAttribute("atrCliente", cliLista);

                    break;

                case "Eliminar": //Borrar registro con criterio
                    
                    idCliente = Integer.valueOf(request.getParameter("ClienteID"));
                    oClienteDAO.borrar(idCliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&btnAccion=Listar").forward(request, response);
                    
                    break;

                case "Buscar": //Buscar registro con criterio
                    break;

                case "Modificar": //Editar registro con criterio

                    idCliente = Integer.valueOf(request.getParameter("ClienteID"));
                    Cliente objCliente = oClienteDAO.listarID(idCliente);
                    request.setAttribute("Cliente", objCliente);
                    request.getRequestDispatcher("Controlador?menu=Cliente&btnAccion=Listar").forward(request, response);

                    break;

                case "Guardar": //Agregar nuevo registro

                    String strCliDNI = request.getParameter("txtClienteDNI");
                    String strCliNombre = request.getParameter("txtClienteNombre").toUpperCase();
                    String strCliDire = request.getParameter("txtClienteDire").toUpperCase();
                    String strCliEstado = request.getParameter("txtClienteEstado");

                    oCliente.setDniCliente(strCliDNI);
                    oCliente.setCliNombre(strCliNombre);
                    oCliente.setCliDireccion(strCliDire);
                    oCliente.setCliEstado(strCliEstado);

                    oClienteDAO.agregar(oCliente);

                    request.getRequestDispatcher("Controlador?menu=Cliente&btnAccion=Listar").forward(request, response);

                    break;
                    
                    case "Actualizar": //Agregar nuevo registro

                    String strCliDNI2 = request.getParameter("txtClienteDNI");
                    String strCliNombre2 = request.getParameter("txtClienteNombre").toUpperCase();
                    String strCliDire2 = request.getParameter("txtClienteDire").toUpperCase();
                    String strCliEstado2 = request.getParameter("txtClienteEstado");

                    oCliente.setDniCliente(strCliDNI2);
                    oCliente.setCliNombre(strCliNombre2);
                    oCliente.setCliDireccion(strCliDire2);
                    oCliente.setCliEstado(strCliEstado2);
                    oCliente.setIdCliente(idCliente);

                    oClienteDAO.actualizar(oCliente);

                    request.getRequestDispatcher("Controlador?menu=Cliente&btnAccion=Listar").forward(request, response);

                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("Cliente.jsp").forward(request, response);
        }

        //Formulario Nueva Venta
        if (menu.equalsIgnoreCase("RegistrarVenta")) { //Menu Nueva Venta
            request.getRequestDispatcher("RegistrarVenta.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
