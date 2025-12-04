<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Turno</title>
</head>

<body>

<a href="${pageContext.request.contextPath}/" class="boton-inicio">
    🏠
    Inicio
</a>

<h1>Crear Nuevo Turno</h1>

<c:if test="${not empty error}">
    <div style="color:red">
        ${error}
    </div>
</c:if>

<c:if test="${param.success == 'created'}">
    <div style="color:green">
        Turno creado correctamente.
    </div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/turnos">
    <input type="hidden" name="action" value="crear"/>

    <!-- aqui es para cargar la fecha del turno -->
    <label>Fecha del turno:</label><br>
    <input type="date" name="fechaTurno" required><br><br>

    <!-- aqui es para escribir la descripción del trámite -->
    <label>Descripción del trámite:</label><br>
    <textarea name="descripcionTramite" required></textarea><br><br>


    <!-- Estado del turno (fijo) -->
    <label>Estado del turno:</label><br>
    <input type="text" value="En espera" readonly style="background: #f0f0f0; color: #666;"><br><br>
    <input type="hidden" name="estadoTurno" value="En espera">

    <!-- aqui esta la lista de ciudadanos, cargada desde la request por el servlet -->
    <label>Ciudadano (DNI):</label><br>
    <select name="dniCiudadano" required>
        <option value="">--Seleccionar ciudadano--</option>
        <c:forEach var="c" items="${ciudadanos}">
            <option value="${c.dni}">${c.nombreCompleto} (${c.dni})</option>
        </c:forEach>
    </select><br><br>

    <!-- aqui el boton para enviar los datos -->
    <button type="submit">Crear Turno</button>
</form>

<br>
<a href="${pageContext.request.contextPath}/turnos">Volver al listado de turnos</a>
</body>
</html>