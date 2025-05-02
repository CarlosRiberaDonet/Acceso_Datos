<%-- 
    Document   : modificarEmpleado
    Created on : 01-may-2025, 15:58:14
    Author     : Carlos Ribera
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Empleado</title>
    </head>
    <body>
        <h2>Credenciales</h2>
        <form action="ActualizarEmpleadoServlet" method="post">
            <label>ID Usuario</label><br>
            <input type="text" name="idUsuario"><br><br>
            
            <label>Nuevo Nombre de Usuario</label><br>
            <input type="text" name="nuevoNombreUsuario"><br><br>
            
            <label>Nueva Contraseña:</label><br>
            <input type="text" name="nuevaContrasena"><br><br>
            
            <label>Nuevo nombre completo:</label><br>
            <input type="text" name="nuevoNombreCompleto"><br><br>
            
            <label>Nuevo teléfono:</label><br>
            <input type="text" name="nuevoTelefono"><br><br>
            
            <input type="submit" value="Actualizar"> 
        </form>
        <button onclick="history.back()">Volver</button>
    </body>
</html>
