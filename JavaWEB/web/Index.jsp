
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" crossorigin="anonymous">
        <!--
        <style type="text/css">
        <%
            //@include file="Bootstrap/css/bootstrap.min.css"
        %>
    </style>
        -->        
        <title>Saludo JSP</title>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
                <div class="col-sm">
                    <form action="Index.jsp" method="POST">
                        <div class="form-group">
                            <label for="exampleInputEmail1">Escribe tu nombre</label>
                            <input type="text" class="form-control" name="nomHumano" placeholder="Nombre">
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">Ingresa tu edad</label>
                            <input type="text" class="form-control" name="edadHumano" placeholder="Edad">
                        </div>
                        <button type="submit" class="btn btn-primary">Enviar</button>
                    </form>
                </div>
            </div>
            <div class="row">
                <div class="col-sm">
                    <div class="alert alert-success" role="alert">
                        <%
                            String nombre = request.getParameter("nomHumano");
                            String edad = request.getParameter("edadHumano");
                            if (nombre != null && edad != null) {
                                String saludo = "Bienvenido. " + nombre + ". A tus " + edad + " años, estas programando en Java Web";
                                out.println(saludo);
                            }else{
                                out.println("Ingrese los datos solicitados.");
                            }
                        %>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
