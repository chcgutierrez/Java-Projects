<%-- 
    Document   : Empleado
    Created on : 4 ago. 2020, 20:53:55
    Author     : Chris
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
        <title>Empleado</title>
    </head>
    <body>
        <div class="d-flex">            
            <div class="card col-sm-5">
<!--                <div>
                    <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                        <div class="btn-group mr-2" role="group" aria-label="First group">
                            <button type="button" class="btn btn-secondary">1</button>
                            <button type="button" class="btn btn-secondary">2</button>
                            <button type="button" class="btn btn-secondary">3</button>
                            <button type="button" class="btn btn-secondary">4</button>
                        </div>
                        <div class="btn-group mr-2" role="group" aria-label="Second group">
                            <button type="button" class="btn btn-secondary">5</button>
                            <button type="button" class="btn btn-secondary">6</button>
                            <button type="button" class="btn btn-secondary">7</button>
                        </div>
                        <div class="btn-group" role="group" aria-label="Third group">
                            <button type="button" class="btn btn-secondary">8</button>
                        </div>
                    </div>
                </div>-->
                <div class="card-body">
                    <form action="Controlador?menu=Empleado" method="POST">
                        <div class="form-group">
                            <label>DNI</label>
                            <input type="text" value="${Empleado.getDni()}" name="txtEmpDNI" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" value="${Empleado.getEmpNombre()}" name="txtEmpNombre" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Teléfono</label>
                            <input type="text" value="${Empleado.getEmpTelefono()}" name="txtEmpTelefono" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <input type="text" value="${Empleado.getEstado()}" name="txtEmpEstado" class="form-control">

                            <!--                            <div class="form-check form-check-inline">
                                                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                                            <label class="form-check-label" for="inlineRadio1">Activo</label>
                                                        </div>
                                                        <div class="form-check form-check-inline">
                                                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
                                                            <label class="form-check-label" for="inlineRadio2">Inactivo</label>
                                                        </div>-->
                        </div>
                        <div class="form-group">
                            <label>Usuario</label>
                            <input type="text" value="${Empleado.getUser()}" name="txtEmpUsuario" class="form-control">
                        </div>
                        <input type="submit" name="btnAccion" value="Registrar" class="btn btn-info">
                        <input type="submit" name="btnAccion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>            
            </div>
            <div class="col-sm-7">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>DNI</th>
                            <th>Nombres</th>
                            <th>Teléfono</th>
                            <th>Estado</th>
                            <th>Usuario</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>                        
                        <c:forEach var="oEmpleado" items="${atrEmpleado}"> <!--Nombre del atributo en el Controlador-->
                            <tr>
                                <td>${oEmpleado.getId()}</td>
                                <td>${oEmpleado.getDni()}</td>
                                <td>${oEmpleado.getEmpNombre()}</td>
                                <td>${oEmpleado.getEmpTelefono()}</td>
                                <td>${oEmpleado.getEstado()}</td>
                                <td>${oEmpleado.getUser()}</td>
                                <td>
                                    <a class="btn btn-warning" href="Controlador?menu=Empleado&btnAccion=Modificar&EmpID=${oEmpleado.getId()}">Editar</a>
                                    <a class="btn btn-danger" href="Controlador?menu=Empleado&btnAccion=Eliminar&EmpID=${oEmpleado.getId()}">Borrar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table> 
            </div>
        </div>

        <script src="js/jquery-3.5.1.slim.min.js" type="text/javascript"></script>
        <script src="js/popper.min.js" type="text/javascript"></script>
        <script src="js/bootstrap.min.js" type="text/javascript"></script>
    </body>
</html>
