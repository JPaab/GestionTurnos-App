<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- aqui formulario para crear un nuevo turno --%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear Turno</title>
    <%-- Favicon de la aplicación (icono de la pestaña del navegador) --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/img/favicon-32x32.png">
</head>

<body>

<form action="${pageContext.request.contextPath}/turnos" method="post">
    <a href="${pageContext.request.contextPath}/"
       class="boton-inicio btn btn-secondary">
        🏠
        Inicio
    </a>
    <div class="page-content">
        <div class="container">

            <h1>Crear Nuevo Turno</h1>

            <%-- aqui muestro el mensaje de error si algo salio mal al crear el turno --%>
            <c:if test="${not empty error}">
                <div style="color:red">
                    ${error}
                </div>
            </c:if>

            <%-- aqui mensaje de exito cuando se crea un turno correctamente --%>
            <c:if test="${param.success == 'created'}">
                <div style="color:green">
                    Turno creado correctamente.
                </div>
            </c:if>

            <%-- aqui empieza el form que se envia al servlet /turnos --%>
            <form method="post" action="${pageContext.request.contextPath}/turnos">
                <input type="hidden" name="action" value="crear"/>

                <%-- aqui el usuario elige la fecha del turno, aparte de la validacion en TurnoServlet, tambien se uso
                un metodo para que el navegador del user no pueda elegir fechas anteriores al dia actual--%>
                <label>Fecha del turno:</label><br>
                <input type="date" name="fechaTurno"
                       min="<%= java.time.LocalDate.now() %>" required><br><br>

                <%-- aqui se escribe la descripcion del tramite --%>
                <label>Descripción del trámite:</label><br>
                <textarea name="descripcionTramite" required></textarea><br><br>


                <%-- aqui el estado siempre es En espera (solo lectura) --%>
                <label>Estado del turno:</label><br>
                <input type="text" value="En espera" readonly style="background: #f0f0f0; color: #666;"><br><br>
                <input type="hidden" name="estadoTurno" value="En espera">

                <%-- aqui se elige el ciudadano por dni que cargo el servlet --%>
                <label>Ciudadano (DNI):</label><br>
                <select name="dniCiudadano" required>
                    <option value="">--Seleccionar ciudadano--</option>
                    <c:forEach var="c" items="${ciudadanos}">
                        <option value="${c.dni}">${c.nombreCompleto} (${c.dni})</option>
                    </c:forEach>
                </select><br><br>

                <%-- aqui boton para enviar el formulario y crear el turno --%>
                <button type="submit">Crear Turno</button>
            </form>

            <br>
            <%-- aqui boton para volver al listado general de los turnos --%>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/turnos">Volver al listado de turnos
            </a>
        </div>
    </div>
    </div>
</form>
</body>
</html>


