<%-- 
    Document   : eliminarEmpleado
    Created on : 02-may-2025, 13:54:08
    Author     : Carlos Ribera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ELIMINAR EMPLEADO</title>
    </head>
    <body>
         <form action="EliminarEmpleadoServlet" method="post">
            
            <label>Nombre de usuario</label><br>
            <input type="text" name="nombreUsuario"><br><br>

            <input type="submit" value="Eliminar">
            
        </form>
        
        <br><button onclick="history.back()">Volver</button>
    </body>
</html>
