<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- aqui formulario para elegir el estado y la fecha para filtrar turnos --%>
<html lang="es">
<form action="${pageContext.request.contextPath}/filtro" method="post">
    <a href="${pageContext.request.contextPath}/" class="boton-inicio">
        🏠
        Inicio
    </a>
    <h2>Filtrado de turnos</h2>

    <label>Estado:</label>
    <%-- aqui selecciono el estado para el filtro (en espera, ya atendido o todos) --%>
    <select name="estado">
        <option value="todos">--Todos--</option>
        <option value="EN_ESPERA">En espera</option>
        <option value="ATENDIDO">Ya atendido</option>
    </select>

    <label>Fecha: </label>
    <%-- aqui filtro opcional por una fecha concreta --%>
    <input type="date" name="fecha">

    <%-- aqui boton para ejecutar el filtro --%>
    <button type="Aceptar">Filtrar</button>

    <br>
    <%-- aqui enlace para ir al formulario de crear un nuevo turno --%>
    <a href="${pageContext.request.contextPath}/turnos?action=nuevo">
        Crear nuevo turno
    </a>
    <br><br>
    <br>
    <%-- aqui boton para volver al listado general de los turnos --%>
    <a href="${pageContext.request.contextPath}/turnos">Volver al listado de turnos
    </a>
</form>

<%-- aqui tabla de resultados solo si el servlet mando turnosFiltrados --%>
<c:if test="${not empty turnosFiltrados}">
    <h3>Resultados del filtro:</h3>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Estado</th>
            <th>Ciudadano</th>
        </tr>

        <%-- aqui recorro los turnos filtrados que vienen del servlet --%>
        <c:forEach var="t" items="${turnosFiltrados}">
            <tr>
                <td>${t.numero}</td>
                <td>${t.fecha}</td>
                <td>${t.estado}</td>
                <td>${t.ciudadano.nombre}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>