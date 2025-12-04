<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Turnos APP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<jsp:include page="partials/header.jsp"/>

<main class="container">
    <section class="hero">
        <h1>Sistema de Turnos</h1>
    </section>

    <section class="cards">
        <div class="card">
            <h2>Ciudadanos</h2>
            <div class="card-actions">
                <a class="btn" href="${pageContext.request.contextPath}/ciudadanos">
                    Ver ciudadanos
                </a>
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/ciudadanos?action=nuevo">
                    Nuevo ciudadano
                </a>
            </div>
        </div>

        <div class="card">
            <h2>Turnos</h2>
            <div class="card-actions">
                <!-- Lista general de turnos (listar-turnos.jsp vía TurnoServlet) -->
                <a class="btn" href="${pageContext.request.contextPath}/turnos">
                    Ver turnos
                </a>
                <!-- Form para crear turno (agregar-turno.jsp vía TurnoServlet) -->
                <a class="btn btn-secondary" href="${pageContext.request.contextPath}/turnos?action=nuevo">
                    Nuevo turno
                </a>
                <!-- Form de filtro (filtrar-turnos.jsp vía FiltroServlet) -->
                <a class="btn btn-outline" href="${pageContext.request.contextPath}/filtro">
                    Filtrar turnos
                </a>
            </div>
        </div>
    </section>
</main>

<jsp:include page="partials/footer.jsp"/>

</body>
</html>
