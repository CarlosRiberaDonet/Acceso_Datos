<%-- 
    Document   : listaIncidencias
    Created on : 02-may-2025, 16:44:17
    Author     : Carlos Ribera
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Lista de Incidencias</title></head>
<body>
    <h2>Lista de Incidencias</h2>
    <c:forEach var="i" items="${incidencias}">
        <hr>
        <p>Id: ${i.id}</p>
        <p>Fecha: ${i.fecha}</p>
        <p>Empleado Origen: ${i.idEmpleadoOrigen.nombreCompleto}</p>
        <p>Empleado Destino: ${i.idEmpleadoDestino.nombreCompleto}</p>
        <p>Detalle: ${i.detalle}</p>
        <p>Tipo: ${i.tipo}</p>
    </c:forEach>
    <br><a href="menu.jsp">Volver al menú</a>
</body>
</html>

