<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%-- aqui pagina principal de inicio de la aplicacion de turnos --%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Gestion De Turnos</title>
    <link rel="icon" type="image/png" sizes="32x32"
          href="${pageContext.request.contextPath}/img/favicon-32x32.png">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<%-- aqui incluyo el header con el menu --%>
<jsp:include page="partials/header.jsp"/>

<main class="container">
    <%-- aqui seccion principal con el titulo grande --%>
    <section class="hero">
        <h1>Sistema de Turnos</h1>
    </section>

    <%-- aqui seccion principal con el titulo grande --%>
    <section class="cards">
        <div class="card">
            <h2>Ciudadanos</h2>
            <div class="card-actions">
                <%-- aqui link para ver el listado de ciudadanos --%>
                <a class="btn" href="${pageContext.request.contextPath}/ciudadanos">
                    Ver ciudadanos
                </a>
                <%-- aqui link para ir al formulario de nuevo ciudadano --%>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/ciudadanos?action=nuevo">
                    Nuevo ciudadano
                </a>
            </div>
        </div>

        <div class="card">
            <h2>Turnos</h2>
            <div class="card-actions">
                <%-- aqui link para ver el listado general de turnos --%>
                <a class="btn" href="${pageContext.request.contextPath}/turnos">
                    Ver turnos
                </a>
                <%-- aqui link para ir al formulario de crear un turno nuevo --%>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/turnos?action=nuevo">
                    Nuevo turno
                </a>
                <%-- aqui link para ir al formulario de filtro de turnos --%>
                <a class="btn btn-outline" href="${pageContext.request.contextPath}/filtro">
                    Filtrar turnos
                </a>
            </div>
        </div>
    </section>
</main>

<%-- aqui incluyo el footer  de la app --%>
<jsp:include page="partials/footer.jsp"/>

</body>
</html>
