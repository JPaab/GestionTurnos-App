<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- aqui estaria el formulario para dar de alta un nuevo ciudadano --%>
<html lang="es">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <meta charset="UTF-8">
    <title>Crear Ciudadanos</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/img/favicon-32x32.png">
</head>
<body>
<form action="${pageContext.request.contextPath}/filtro" method="post">
    <a href="${pageContext.request.contextPath}/"
       class="boton-inicio btn btn-secondary">
        🏠
        Inicio
    </a>
    <div class="page-content">
<div class="container">
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
    <button type="submit">Guardar ciudadano</button>
</form>

    <br>
    <%-- aqui link para volver al listado de ciudadanos --%>
<a class="btn btn-secondary" href="${pageContext.request.contextPath}/ciudadanos">Volver al listado de ciudadanos
</a>
</div>
</div>
</form>
</body>
</html>