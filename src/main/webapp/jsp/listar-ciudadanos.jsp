<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html lang="es" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <title>Listado de ciudadanos.</title>
</head>
<body>
<h1>Ciudadanos</h1>

<c:if test="${param.success == 'created'}">
    <div style="color:green">
        Ciudadano creado correctamente.
    </div>
</c:if>

<c:if test="${not empty error}">
    <div style="color:red">
        ${error}
    </div>
</c:if>

<c:if test="${empty ciudadanos}">
    <p>No hay ciudadanos registrados!</p>
</c:if>

<c:if test="${not empty ciudadanos}">
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
        <tr>
            <th>ID</th>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Cantidad de turnos</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${ciudadanos}">
            <tr>
            <td>${c.id}</td>
            <td>${c.dni}</td>
            <td>${c.nombreCompleto}</td>
            <td>${c.email}</td>
            <td>${c.cantidadTurnos}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<br>
<a href="${pageContext.request.contextPath}/ciudadanos?action=nuevo">
    Crear nuevo ciudadano
</a>
<br><br>
<a href="${pageContext.request.contextPath}/turnos">
    Ir al listado de turnos
</a>
</body>
</html>