<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Crear ciudadanos.</title>
</head>
<body>
<h1>Crear nuevo ciudadano</h1>

<c:if test="${not empty error}">
    <div style="color:red">
        ${error}
    </div>
</c:if>

<form method="post" action="${pageContext.request.contextPath}/ciudadanos">
    <label>Nombre:</label><br>
    <input type="text" name="nombre" value="${nombre}"/><br><br>

    <label>Apellido:</label><br>
    <input type="text" name="apellido" value="${apellido}"/><br><br>

    <label>DNI:</label><br>
    <input type="text" name="dni" value="${dni}"/><br><br>

    <label>Email:</label><br>
    <input type="email" name="email" value="${email}"/><br><br>

    <button type="submit">Guardar ciudadano.</button>
</form>
<a href="${pageContext.request.contextPath}/ciudadanos">Volver al listado de ciudadanos.</a>
</body>
</html>