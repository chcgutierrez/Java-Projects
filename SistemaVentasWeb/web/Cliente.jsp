<%-- 
    Document   : Cliente
    Created on : 4 ago. 2020, 20:54:07
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
        <title>Cliente</title>
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
                    <form action="Controlador?menu=Cliente" method="POST">
                        <div class="form-group">
                            <label>DNI</label>
                            <input type="text" value="${Cliente.getDniCliente()}" name="txtClienteDNI" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Nombre</label>
                            <input type="text" value="${Cliente.getCliNombre()}" name="txtClienteNombre" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Dirección</label>
                            <input type="text" value="${Cliente.getCliDireccion()}" name="txtClienteDire" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <input type="text" value="${Cliente.getCliEstado()}" name="txtClienteEstado" class="form-control">

                            <!--                            <div class="form-check form-check-inline">
                                                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1" value="option1">
                                                            <label class="form-check-label" for="inlineRadio1">Activo</label>
                                                        </div>
                                                        <div class="form-check form-check-inline">
                                                            <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2" value="option2">
                                                            <label class="form-check-label" for="inlineRadio2">Inactivo</label>
                                                        </div>-->
                        </div>
                        <input type="submit" name="btnAccion" value="Guardar" class="btn btn-info">
                        <input type="submit" name="btnAccion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>            
            </div>
            <div class="col-sm-7">
                <table class="table table-hover">
                    <thead class="thead-dark">
                        <tr>
                            <th>ID</th>
                            <th>DNI</th>
                            <th>Nombres</th>
                            <th>Direccion</th>
                            <th>Estado</th>
                            <th>Acción</th>
                        </tr>
                    </thead>
                    <tbody>                        
                        <c:forEach var="oCliente" items="${atrCliente}"> <!--Nombre del atributo en el Controlador-->
                            <tr>
                                <td>${oCliente.getIdCliente()}</td>
                                <td>${oCliente.getDniCliente()}</td>
                                <td>${oCliente.getCliNombre()}</td>
                                <td>${oCliente.getCliDireccion()}</td>
                                <td>${oCliente.getCliEstado()}</td>
                                <td>
                                    <a class="btn btn-warning btn-sm" href="Controlador?menu=Cliente&btnAccion=Modificar&ClienteID=${oCliente.getIdCliente()}">Editar</a>
                                </td>
                                <td>
                                    <a class="btn btn-danger btn-sm" href="Controlador?menu=Cliente&btnAccion=Eliminar&ClienteID=${oCliente.getIdCliente()}">Borrar</a>
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
