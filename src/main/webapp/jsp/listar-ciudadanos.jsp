<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es">
<head>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Listar ciudadanos</title>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body>
<!-- header -->
<%@ include file="partials/header.jsp" %>
<!-- contenido página principal -->
<main>
    <h2>Lisat de ciudadanos</h2>
    <table>
        <thead>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>DNI</th>
        <th>Email</th>
        </thead>
        <body>

        </body>
    </table>
</main>
<!-- footer -->
<%@ include file="partials/footer.jsp" %>
</body>
</html>