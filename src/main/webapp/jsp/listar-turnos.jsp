<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="es">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <%-- Favicon de la aplicación (icono de la pestaña del navegador) --%>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/img/favicon-32x32.png">
    <meta charset="UTF-8">
    <title>Listado De Turnos</title>
</head>

<body>

<!-- Formulario para el filtro de turnos -->
<form action="${pageContext.request.contextPath}/filtro" method="post">
    <a href="${pageContext.request.contextPath}/" class="boton-inicio">🏠 Inicio</a>
</form>

<div class="page-content">
    <div class="container">
        <h1>Turnos</h1>

        <!-- Mensaje cuando se crea un turno nuevo -->
        <c:if test="${param.success == 'created'}">
            <div class="alert alert-success">
                Turno creado correctamente.
            </div>
        </c:if>

        <!-- Mensaje cuando se marca un turno como atendido -->
        <c:if test="${param.success == 'updated'}">
            <div style="color:green">
                Turno atendido correctamente.
            </div>
        </c:if>

        <!-- Mostrar errores que vienen desde los servlets -->
        <c:if test="${not empty error}">
            <div style="color:red">
                ${error}
            </div>
        </c:if>

        <!-- Mensaje si no hay turnos cargados -->
        <c:if test="${empty turnos}">
            <div class="alert alert-error">
                No hay turnos registrados.
            </div>
        </c:if>

        <c:if test="${not empty turnos}">
            <!-- Mostrar tabla con los turnos cuando la lista no está vacía -->
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
                <!-- Recorrer todos los turnos enviados por el servlet -->
                <c:forEach var="t" items="${turnos}">
                    <tr>
                        <td>${t.identificadorProgresivo}</td>
                        <td>
                            <c:choose>
                                <c:when test="${t.estado == 'EN_ESPERA'}">
                                    En espera
                                </c:when>
                                <c:when test="${t.estado == 'ATENDIDO'}">
                                    Atendido
                                </c:when>
                                <c:otherwise>
                                    ${t.estado}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${t.fechaFormateada}</td>
                        <td>${t.descripcionTramite}</td>
                        <td>${t.ciudadano.nombre}</td>
                        <td>
                            <!-- Mostrar el botón Atender solo si el turno está en espera -->
                            <c:if test="${t.estaEnEspera()}">
                                <form action="${pageContext.request.contextPath}/turnos/actualizar" method="post">
                                    <input type="hidden" name="id" value="${t.id}">
                                    <button type="submit" class="btn btn-primary">Atender</button>
                                </form>
                            </c:if>

                            <!-- Mostrar texto cuando el turno ya fue atendido -->
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

        <!-- Enlace para ir al formulario de crear un nuevo turno -->
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/turnos?action=nuevo">
            Crear nuevo turno
        </a>

        <br><br>

        <!-- Enlace para ir a la página de filtro de turnos -->
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/filtro">
            Ir al filtro de turnos
        </a>

    </div>
</div>
</body>
</html>


