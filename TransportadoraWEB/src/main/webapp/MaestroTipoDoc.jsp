<%-- 
    Document   : Database
    Created on : 31 may. 2020, 20:42:56
    Author     : Chris
--%>

<%@page import="java.sql.*"%>
<%@page import="com.mysql.jdbc.Driver"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" crossorigin="anonymous">    
        <title>Maestra - Tipo Documento</title>
    </head>
    <body>
        <%
            //Conexion a BD
            Connection ConBD = null;
            Statement stmSQL = null;
        %>        
        <div class="container mt-5">
            <div class="row">
                <div class="col-sm">
                    <form action="MaestroTipoDoc.jsp" method="POST">
                        <div class="form-group">
                            <label for="codigo">Código</label>
                            <input type="text" class="form-control" name="codTipoDoc" placeholder="Código" required="">
                        </div>
                        <div class="form-group">
                            <label for="descripcion">Descripción</label>
                            <input type="text" class="form-control" name="descTipoDoc" placeholder="Descripción" required="">
                        </div>
                        <div class="form-group">
                            <label for="observ">Observaciones</label>
                            <input type="text" class="form-control" name="obsTipoDoc" placeholder="Observaciones">
                        </div>
                        <button type="submit" name="btnGrdTipoDoc" class="btn btn-primary">Guardar</button>
                    </form>
                </div>
            </div>           
        </div>
        <%
            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String fecActual = ft.format(dNow);
        %>
        <%
            if (request.getParameter("btnGrdTipoDoc") != null) {
                String codigo = request.getParameter("codTipoDoc").toUpperCase();
                String descripcion = request.getParameter("descTipoDoc").toUpperCase();
                String fec_con = fecActual;
                String login = "ADMIN";
                String obser = request.getParameter("obsTipoDoc").toUpperCase();

                try {
                    String dtbURL = "jdbc:mysql://localhost:3306/transportadora?serverTimezone=UTC";
                    String dtbUser = "root";
                    String dtbPass = "123456";
                    String strSQL = "INSERT INTO maestratipodocumento (cod_tipodoc, Descripcion, fec_con, login, obs) "
                            + "VALUES('" + codigo + "', '" + descripcion + "', '" + fec_con + "', '" + login + "', '" + obser + "');";
                    Class.forName("com.mysql.jdbc.Driver");
                    ConBD = DriverManager.getConnection(dtbURL, dtbUser, dtbPass);
                    stmSQL = ConBD.createStatement();
                    stmSQL.executeUpdate(strSQL);
                    request.getRequestDispatcher("TipoDocumento.jsp").forward(request, response);
                } catch (Exception e) {
                    out.println(e);
                }
            }
        %>

    </body>
</html>
