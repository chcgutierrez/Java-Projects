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
                            <%                            //Conexion a BD
                                try {
                                    String dtbURL = "jdbc:mysql://localhost:3306/transportadora?serverTimezone=UTC";
                                    String dtbUser = "root";
                                    String dtbPass = "123456";
                                    String strSQL = "SELECT cod_tipodoc, Descripcion, CAST(fec_con AS DATE) AS fec_con, obs FROM maestratipodocumento;";
                                    Class.forName("com.mysql.jdbc.Driver");
                                    ConBD = DriverManager.getConnection(dtbURL, dtbUser, dtbPass);
                                    stmSQL = ConBD.createStatement();
                                    rstSQL = stmSQL.executeQuery(strSQL);

                                    while (rstSQL.next()) {
                            %>

                            <tr align="center">
                                <th scope = "row"><%=rstSQL.getString("cod_tipodoc")%></th>
                                <td align="left"><%=rstSQL.getString("Descripcion")%></td>
                                <td><%=rstSQL.getString("fec_con")%></td>
                                <td><%=rstSQL.getString("obs")%></td>
                            </tr>
                            
                            <%
                                    }
                                } catch (Exception e) {
                                    out.println(e);
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>           
        </div>
    </body>
</html>

