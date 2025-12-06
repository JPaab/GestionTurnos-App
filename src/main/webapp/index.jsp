<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Turnos APP</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<%-- Aqui incluyo el header con el menu --%>
<jsp:include page="partials/header.jsp"/>

<main class="page-content">
    <div class="container home-container">
        <section class="home-grid">
            <div class="home-card">
                <h2>Ciudadanos</h2>

                <div class="section-actions">
                    <div class="section-actions-primary">
                        <a href="${pageContext.request.contextPath}/ciudadanos?action=nuevo"
                           class="btn btn-secondary btn-sm">
                            Nuevo ciudadano
                        </a>
                    </div>
                    <div class="section-actions-secondary">
                        <a href="${pageContext.request.contextPath}/ciudadanos"
                           class="btn btn-outline btn-sm">
                            Ver ciudadanos
                        </a>
                    </div>
                </div>
            </div>

            <!-- Tarjeta Turnos -->
            <div class="home-card">
                <h2>Turnos</h2>

                <div class="section-actions">
                    <div class="section-actions-primary">
                        <a href="${pageContext.request.contextPath}/turnos?action=nuevo"
                           class="btn btn-secondary btn-sm">
                            Nuevo turno
                        </a>
                    </div>
                    <div class="section-actions-secondary">
                        <a href="${pageContext.request.contextPath}/turnos"
                           class="btn btn-outline btn-sm">
                            Ver turnos
                        </a>
                        <a href="${pageContext.request.contextPath}/filtro"
                           class="btn btn-outline btn-sm">
                            Filtrar turnos
                        </a>
                    </div>
                </div>
            </div>
        </section>

    </div>
</main>

<jsp:include page="partials/footer.jsp"/>

</body>
</html>