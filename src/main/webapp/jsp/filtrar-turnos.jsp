
<form action="TurnoServlet" method="post" xmlns:c="">
<h2>Filtrado de turnos</h2>
<form action="TurnoServlet" method="get"></form>
<input type="hidden" made="action" value="filtrar">

<!-- Dropdown para elegir el estado por el que se filtrará (opcional, si no se elige nada, mostrará todos los ciudadanos) -->
<label>Estado:</label>
<select name="estado">
    <option value="">--Todos--</option>
    <option value="En espera">En espera</option>
    <option value="Ya atendido">Ya atendido</option>
</select>

<label>Fecha: </label>
<input type="date" name="fecha">

<button type="Aceptar">Filtrar</button>
</form>

<!-- Mostrar los resultados solo si existen -->
<c:if test="${not empty turnosFiltrados}">
    <h3>Resultados del filtro:</h3>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Fecha</th>
            <th>Estado</th>
            <th>Ciudadano</th>
        </tr>

        <!-- Iteración sobre los turnos filtrados enviados desde el servlet -->
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