<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- aqui estaria el formulario para dar de alta un nuevo ciudadano --%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear ciudadanos.</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/" class="boton-inicio">
    🏠
    Inicio
</a>
<h1>Crear nuevo ciudadano</h1>

<%-- aqui muestro el error si falta algun dato o hay problema con el formato --%>
<c:if test="${not empty error}">
    <div style="color:red">
        ${error}
    </div>
</c:if>
<%-- aqui el form se envia al servlet /ciudadanos por POST --%>
<form method="post" action="${pageContext.request.contextPath}/ciudadanos">
    <%-- aqui inputs basicos: nombre, apellido, dni y email --%>
    <label>Nombre:</label><br>
    <input type="text" name="nombre" value="${nombre}"/><br><br>

    <label>Apellido:</label><br>
    <input type="text" name="apellido" value="${apellido}"/><br><br>

    <label>DNI:</label><br>
    <input type="text" name="dni" value="${dni}"/><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" value="${email}"/><br><br>

    <%-- aqui boton para guardar el ciudadano --%>
    <button type="submit">Guardar ciudadano.</button>

    <%-- aqui link para volver al listado de ciudadanos --%>
</form>
<a href="${pageContext.request.contextPath}/ciudadanos">Volver al listado de ciudadanos.</a>
</body>
</html>