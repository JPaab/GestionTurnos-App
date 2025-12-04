# Sistema de Turnos Web (Java / Servlets / JPA) 📆

Aplicación web sencilla para la gestión de turnos de ciudadanos, 
desarrollada con **Java**, **Servlet**, **JSP** y **JPA**.

---

* Registrar ciudadanos.
* Asignar turnos a ciudadanos.
* Listar turnos.
* Marcar turnos como atendidos.
* Filtrar turnos por estado y fecha.

---
## ✅ Requisitos y ejecución

Para ejecutar el programa, confirma que los siguientes puntos estan correctos.

1. **Clonar el repositorio**
```
git clone <URL_DEL_REPO>
cd <NOMBRE_DEL_PROYECTO>
```
2. **Configurar la base de datos**
3. **Uso en la página web**


---

## ⚙️ Tecnologías utilizadas.

1. **Java** (8+)

2. **Maven** ('maven-archetype-webapp')

3. **Jakarta/Java EE Servlets**

4. **JSP + JSTL**

5. **JPA** (con 'EntityManager' y 'persistence.xml')

6. **Servidor de aplicaciones**: Apache Tomcat


---

## 📁 Estructura

```
com.turnos
 ├─ entities/
 │   └─ Ciudadano.java                             # Estructura basica del Ciudadano, incluyendo validaciones basicas.
 │   └─ Turno.java                                 # Representa un turno: identificador progresivo, fechas, descripciones, estado y el ciudadano asociado
 │   └─ TurnoEstado.java                           # Enum con los estados posibles que tiene un Turno (EN_ESPERA, ATENDIDO)
 ├─ persistence/
 │   └─ CiudadanoRepositoryJPA.java                # Capa de acceso a los datos del ciudadano, maneja operaciones basicas.
 │   └─ ConfigJPA.java                             # Configuracion del JPA, EMF y EM.
 │   └─ TurnoRepositoryJPA.java                    # Capa de acceso a datos de los Turnos, maneja operaciones basicas.
 ├─ servlets/
 │   └─ ActualizarTurnoServlet.java                # Servlet para /turnos/actualizar
 │   └─ CiudadanoServlet.java                      # Servlet para /ciudadanos
 │   └─ FiltroServlet.java                         # Servlet para /filtro
 │   └─ TurnoServlet.java                          # Servlet para /turnos
 ├─ webapp/
 │   └─ index.jsp                                  # Página de entrada de la aplicación
 ├─ css/
 │   └─ style.css                                  # style.css para la app, aquí esta toda la estetica de la aplicación.
 ├─ jsp/
 │   └─ agregar-turno.jsp                          # Formulario para crear un nuevo turno
 │   └─ crear-ciudadano.jsp                        # Formulario para registrar un nuevo ciudadano
 │   └─ filtrar-turnos.jsp                         # Formulario para filtrar turnos por estado y fecha
 │   └─ listar-ciudadanos.jsp                      # Tabla con los ciudadanos existentes
 │   └─ turnos-lista.jsp                           # Tabla con los turnos, muestra datos del ciudadano, estado etc.
 ├─ partials/
 │   └─ footer.jsp                                 # Pie de la página.
 │   └─ header.jsp                                 # Cabecera de la página.
    
```

---

## 🦜 Funcionalidades principales.

### Gestión de ciudadanos

* Alta de ciudadanos.
* `nombre`, `apellido`, `DNI`, `Email`
* Validaciones básicas en el servidor.
* Listado de ciudadanos, incluyendo cantidad de turnos asociados.

### Gestión de turnos

* Alta de turnos asociados a un Ciudadano por DNI.
* Identificador progresivo para cada turno (T001).
* Estado del turno: `EN ESPERA`, `ATENDIDO`

### Filtro de turnos

* Estado `EN ESPERA`, `ATENDIDO`
* Fecha basada en `LocalDate`
* Los resultados filtrados se muestran en el mismo formato que el listado general.

---
> **Anotaciones:**
>
> * 
> * 
> * 

---
