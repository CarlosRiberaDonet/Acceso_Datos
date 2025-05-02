<%-- 
    Document   : empleado
    Created on : 30-abr-2025, 15:29:28
    Author     : Carlos Ribera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alta de empleado</title>
    </head>
    <body>
        <h2>Insertar nuevo empleado</h2>
        <form action="ServletEJB" method="post">
                <label>Nombre de usuario</label><br>
                <input type="text" name="usuario"><br><br>

                <label>Contraseña</label><br>
                <input type="password" name="contrasena"><br><br>

                <label>Nombre completo</label><br>
                <input type="text" name="nombre"><br><br>

                <label>Teléfono</label><br>
                <input type="text" name="telefono"><br><br>

                <input type="submit" value="Guardar">    
        </form>
        
        <button onclick="history.back()">Volver</button>
</body>
</html>