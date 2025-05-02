<%-- 
    Document   : crearIncidencia
    Created on : 02-may-2025, 16:49:49
    Author     : Carlos Ribera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Crear Incidencia</title>
    </head>
    <body>
        <h2>Nueva Incidencia</h2>
        <form action="NuevaIncidenciaServlet" method="post">
                <label>Fecha</label><br>
                <input type="date" name="fecha"><br><br>

                <label>Empleado Origen</label><br>
                <input type="text" name="empleadoOrigen"><br><br>

                <label>Empleado Destino</label><br>
                <input type="text" name="empleadoDestino"><br><br>

                <label>Detalle</label><br>
                <input type="text" name="detalle"><br><br>
                
                <label>Tipo</label><br>
                <input type="text" name="tipo"><br><br>

                <input type="submit" value="Crear">    
        </form>
        
        <button onclick="history.back()">Volver</button>
    </body>
</html>
