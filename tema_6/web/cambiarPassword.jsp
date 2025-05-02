<%-- 
    Document   : cambiarPassword
    Created on : 02-may-2025, 0:03:38
    Author     : Carlos Ribera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cambiar Contraseña</title>
    </head>
    <body>     
        <form action="CambiarPasswordServlet" method="post">
            <label>Nombre de Usuario</label><br>
            <input type="text" name="nombreUsuario"><br><br>
            
            <label>Nueva Contraseña:</label><br>
            <input type="text" name="contrasena"><br><br>
            
            <input type="submit" value="Guardar">
        </form>
        
        <br><button onclick="history.back()">Volver</button>
    </body>
</html>
