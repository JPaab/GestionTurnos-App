<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="es" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <title>Listado de turnos.</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/" class="boton-inicio">
    🏠
    Inicio
</a>
<h1>Turnos</h1>

<c:if test="${param.success == 'created'}">
    <div style="color:green">
        Turno creado correctamente.
    </div>
</c:if>

<c:if test="${param.success == 'updated'}">
    <div style="color:green">
        Turno atendido correctamente.
    </div>
</c:if>

<c:if test="${not empty error}">
    <div style="color:red">
        ${error}
    </div>
</c:if>

<c:if test="${empty turnos}">
    <p>No hay turnos registrados!</p>
</c:if>

<c:if test="${not empty turnos}">
    <table border="1" cellpadding="5" cellspacing="0">
        <thead>
        <tr>
            <th>Identificador</th>
            <th>Estado</th>
            <th>Fecha</th>
            <th>Descripción</th>
            <th>Ciudadano</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="t" items="${turnos}">
            <tr>
                <td>${t.identificadorProgresivo}</td>
                <td>${t.estado}</td>
                <td>${t.fecha}</td>
                <td>${t.descripcionTramite}</td>
                <td>${t.ciudadano.nombre}</td>
                <td>
                    <c:if test="${t.estaEnEspera()}">
                        <form action="${pageContext.request.contextPath}/turnos/actualizar" method="post">
                            <input type="hidden" name="id" value="${t.id}">
                            <button type="submit">Atender</button>
                        </form>
                    </c:if>
                    <c:if test="${not t.estaEnEspera()}">
                        Ya atendido
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

<br>
<a href="${pageContext.request.contextPath}/turnos?action=nuevo">
    Crear nuevo turno
</a>
<br><br>
<a href="${pageContext.request.contextPath}/turnos">
    Ir al listado de turnos
</a>
</body>
</html>