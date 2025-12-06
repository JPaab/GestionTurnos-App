<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- aqui listado de turnos con sus datos principales --%>
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

<%-- aqui mensaje cuando se crea un turno nuevo --%>
<c:if test="${param.success == 'created'}">
    <div style="color:green">
        Turno creado correctamente.
    </div>
</c:if>

<%-- aqui mensaje cuando se marca un turno como atendido --%>
<c:if test="${param.success == 'updated'}">
    <div style="color:green">
        Turno atendido correctamente.
    </div>
</c:if>

<%-- aqui muestro errores que vienen desde los servlets --%>
<c:if test="${not empty error}">
    <div style="color:red">
        ${error}
    </div>
</c:if>

<%-- aqui mensaje si no hay turnos cargados --%>
<c:if test="${empty turnos}">
    <p>No hay turnos registrados!</p>
</c:if>

<c:if test="${not empty turnos}">
    <%-- aqui tabla con los turnos cuando la lista no esta vacia --%>
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
        <%-- aqui recorro todos los turnos que envio el servlet --%>
        <c:forEach var="t" items="${turnos}">
            <tr>
                <td>${t.identificadorProgresivo}</td>
                <td>${t.estado}</td>
                <td>${t.fechaFormateada}</td>
                <td>${t.descripcionTramite}</td>
                <td>${t.ciudadano.nombre}</td>
                <td>
                    <%-- aqui muestro el boton Atender solo si el turno esta en espera --%>
                    <c:if test="${t.estaEnEspera()}">
                        <form action="${pageContext.request.contextPath}/turnos/actualizar" method="post">
                            <input type="hidden" name="id" value="${t.id}">
                            <button type="submit">Atender</button>
                        </form>
                    </c:if>

                    <%-- aqui texto cuando el turno ya fue atendido --%>
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
<%-- aqui enlace para ir al formulario de crear un nuevo turno --%>
<a href="${pageContext.request.contextPath}/turnos?action=nuevo">
    Crear nuevo turno
</a>
<br><br>
<br>
<%-- aqui enlace para ir a la pagina de filtro de turnos --%>
<a href="${pageContext.request.contextPath}/filtro">
    Ir al filtro de turnos
</body>
</html>
</body>
</html>