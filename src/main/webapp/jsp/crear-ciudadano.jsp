<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Crear ciudadano</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<!-- header -->
<%@ include file="partials/header.jsp" %>
<!-- contenido página principal -->
<main>
    <h2>Creacion de ciudadano</h2>
    <form action="guardar" method="post">
        <label>Nombre: <input type="text" name="Nombre" required placeholder="String"></label>
        <label>Apellido: <input type="text" name="Apellido" required placeholder="String"></label>
        <label>DNI: <input type="text" name="DNI" required placeholder="String"></label>
        <label>Email: <input type="text" name="Email" required placeholder="String"></label>
        <fieldset>
            <legend>Botones</legend>
            <button type="reset">Limpiar</button>
            <button type="submit">Guardar</button>
        </fieldset>

    </form>
</main>
<!-- footer -->
<%@ include file="partials/footer.jsp" %>
</body>
</html>