<%-- 
    Document   : obtenerIncidencia
    Created on : 02-may-2025, 14:30:57
    Author     : Carlos Ribera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Obtener Incidencia por ID</title>
    </head>
    <body>
        <form action="obtenerIncidenciaServlet" method="post">
            
            <label>ID Incidencia</label><br>
            <input type="text" name="incidenciaId"><br><br>

            <input type="submit" value="Obtener">   
        </form>
        
        <br><button onclick="history.back()">Volver</button>
    </body>
</html>
