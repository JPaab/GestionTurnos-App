<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- aqui listado general de ciudadanos --%>
<html lang="es" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/img/favicon-32x32.png">
    <meta charset="UTF-8">
    <title>Listado De Ciudadanos</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/filtro" method="post">
    <a href="${pageContext.request.contextPath}/"
       class="boton-inicio btn btn-secondary">
        🏠
        Inicio
    </a>
</form>
    <div class="page-content">
<div class="container">
<h1>Ciudadanos</h1>

<%-- aqui mensaje de exito cuando se crea un ciudadano nuevo --%>
    <c:if test="${param.success == 'created'}">
        <div class="alert alert-success">
            Ciudadano creado correctamente.
        </div>
    </c:if>

<%-- aqui muestro el error si llega desde el servlet --%>
<c:if test="${not empty error}">
    <div style="color:red">
        ${error}
    </div>
</c:if>

<%-- aqui mensaje cuando no hay ciudadanos en la BD --%>
    <c:if test="${empty ciudadanos}">
        <div class="alert alert-error">
            No hay ciudadanos registrados.
        </div>
    </c:if>

<%-- aqui tabla con los ciudadanos y su cantidad de turnos --%>
<c:if test="${not empty ciudadanos}">
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
        <tr>
            <th>Id</th>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Email</th>
            <th>Cantidad de turnos</th>
        </tr>
        </thead>
        <tbody>

        <%-- aqui recorro la lista de ciudadanos que envio el servlet --%>
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

<%-- aqui enlace para crear un nuevo ciudadano --%>
<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ciudadanos?action=nuevo">
    Crear nuevo ciudadano
</a>
<br><br>

<%-- aqui enlace para ir al listado de turnos --%>
<a class="btn btn-secondary" href="${pageContext.request.contextPath}/turnos">
    Ir al listado de turnos
</a>
</div>
</div>
</body>
</html>