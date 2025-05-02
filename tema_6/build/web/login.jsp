<%-- 
    Document   : login
    Created on : 30-abr-2025, 21:30:34
    Author     : Carlos Ribera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title> 
    </head>
    <body>
        <form action="LoginServletEJB" method="post">
            
            <label>Nombre de usuario</label><br>
            <input type="text" name="nombreUsuario"><br><br>

            <label>Contraseña</label><br>
            <input type="password" name="contrasena"><br><br>

            <input type="submit" value="Login"><br><br>
            <button type="button" onclick="location.href='index.jsp'">Volver</button>
            
        </form>
        
        
    </body>
</html>
