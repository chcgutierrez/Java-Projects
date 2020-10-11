<%-- 
    Document   : Actividades
    Created on : 11 sep. 2020, 22:47:41
    Author     : Chris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <!-- Custom styles for this template -->
    <!-- Custom styles for this template -->
    <link href="css/actividades.css" rel="stylesheet" type="text/css"/>
    <title>Actividades . Principal</title>

    <!-- Favicons -->
    <!--    <link rel="apple-touch-icon" href="/docs/4.5/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
        <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
        <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
        <link rel="manifest" href="/docs/4.5/assets/img/favicons/manifest.json">
        <link rel="mask-icon" href="/docs/4.5/assets/img/favicons/safari-pinned-tab.svg" color="#563d7c">
        <link rel="icon" href="/docs/4.5/assets/img/favicons/favicon.ico">
        <meta name="msapplication-config" content="/docs/4.5/assets/img/favicons/browserconfig.xml">
        <meta name="theme-color" content="#563d7c">-->

    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>

</head>
<body>
    <div class="container">
        <div class="py-5 text-center">
            <h2>Registrar Actividad</h2>
        </div>

        <div class="row">
            <div class="col-md-8 order-md-1">
                <h4 class="mb-3">Parámetros</h4>
                <form class="needs-validation" novalidate>
                    <div class="mb-3">
                        <label>Solicitado por</label>
                        <div class="input-group">
                            <input type="text" value="${Usuario.getUsuNombre()}" class="form-control" name="txtUserCrea" placeholder="Usuario" required>                           
                            <div class="invalid-feedback" style="width: 100%;">
                                Se requiere un nombre de usuario.
                            </div>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label>Título</label>
                        <input type="text" class="form-control" name="txtTitulo" placeholder="Titulo">
                        <div class="invalid-feedback">
                            Se requiere un nombre de titulo.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label>Detalle</label>
                        <input type="text" class="form-control" name="txtDetalle" placeholder="Detalle" required>
                        <div class="invalid-feedback">
                            Se requiere una descripcion.
                        </div>
                    </div>

                    <div class="mb-3">
                        <label>Asignado a</label>
                        <div class="input-group">
                            <input type="text" value=" ${Usuario.getUsuNombre()}" class="form-control" name="txtUserResu" placeholder="Usuario" required>                           
                            <div class="invalid-feedback" style="width: 100%;">
                                Se requiere un nombre de usuario.
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-md-4 mb-3">
                            <label for="country">Prioridad</label>
                            <select class="custom-select d-block w-100" id="country" required>
                                <option value="">Seleccione</option>
                                <option value="1">Crítica</option>
                                <option value="2">Alta</option>
                                <option value="3">Media</option>
                                <option value="4">Baja</option>
                            </select>
                            <div class="invalid-feedback">
                                Debe seleccionar un valor.
                            </div>
                        </div>
                        <div class="col-md-5 mb-3">
                            <label for="state">Categoria</label>
                            <select class="custom-select d-block w-100" id="state" required>
                                <option value="">Seleccione</option>
                                <option value="1">Desarrollo</option>
                                <option value="2">Despliegue</option>
                                <option value="3">Incidencia</option>
                                <option value="4">Mejora</option>
                                <option value="5">Pruebas</option>
                            </select>
                            <div class="invalid-feedback">
                                Debe seleccionar un valor.
                            </div>
                        </div>
                        <div class="col-md-3 mb-3">
                            <label for="zip">Adjuntos</label>
                            <input type="text" class="form-control" id="zip" placeholder="">
                            <div class="invalid-feedback">
                                Debe asociar un archivo.
                            </div>
                        </div>
                    </div>
                    <hr class="mb-4">
                    <div class="custom-control custom-checkbox">
                        <input type="checkbox" class="custom-control-input" id="same-address">
                        <label class="custom-control-label" for="same-address">Enviar notificaciones</label>
                    </div>

                    <hr class="mb-4">
                    <button type="submit" name="btnIngresar" value="btnGuardarActv" class="btn btn-primary">Generar</button>
                </form>
            </div>
        </div>
    </div>
    <script src="js/jquery-3.5.1.slim.min.js" type="text/javascript"></script>
    <script>window.jQuery || document.write('<script src="/docs/4.5/assets/js/vendor/jquery.slim.min.js"><\/script>')</script>
    <script src="js/bootstrap.bundle.min.js" type="text/javascript"></script>
    <script src="js/actividades.js" type="text/javascript"></script>
    <!--<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>-->
    <!--<script src="/docs/4.5/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>-->
    <!--<script src="form-validation.js"></script>-->
</body>
</html>

