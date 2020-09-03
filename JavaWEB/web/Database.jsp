<%-- 
    Document   : Database
    Created on : 31 may. 2020, 20:42:56
    Author     : Chris
--%>

<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" crossorigin="anonymous">      
        <title>Tipo Documento</title>
    </head>
    <body>
        <%
            //Conexion a BD
            Connection ConBD = null;
            Statement stmSQL = null;
            ResultSet rstSQL = null;
        %>
        <div class="container mt-5">
            <div class="row">
                <div class="col-sm">
                    <table class="table">
                        <thead class="thead-dark" align="center">
                            <tr>
                                <th scope="col">Código</th>
                                <th scope="col">Descripcion</th>
                                <th scope="col">Fecha Reg</th>
                                <th scope="col">Observaciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Mark</td>
                                <td>Otto</td>
                                <td>@mdo</td>
                            </tr>                           
                        </tbody>
                    </table>
                </div>
            </div>           
        </div>
    </body>
</html>

