<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<input type="hidden" name="action" value="guardarTurno"/>
    <label>fecha;</label>

    <!-- Para cargar la fecha del turno -->
    <input type="date" name="fecha" required>

    <!-- Para escribir la descripción del trámite -->
    <label>Descripción</label>
    <textarea name="descripción" required></textarea>

    <!-- Para seleccionar el estado del turno -->
    <label>Esttado del turno</label>
    <select name="estadoTurno">
        <option value="En espera">En espera</option>
        <option value="Ya atendido">Ya atendido</option>
    </select>

    <!-- Lista de ciudadanos, cargada desde la request por el servlet -->
    <label>Ciudadano:</label>
    <select name="ciudadanoID" required>
        <c:forEach var="c" items="${ciudadano}">
            <option value="${c.id}">${c.nombre} ${c.apellido}</option>
        </c:forEach>
    </select>

    <!-- Botón para enviar los datos -->
    <button type="submit">Crear turno</button>
</form>