package com.turnos.persistence;

import com.turnos.entities.Turno;
import jakarta.persistence.EntityManager;

import java.util.List;

//aqui repositorio JPA para trabajar con la entidad Turno
public class TurnoRepositoryJPA {

    //aqui obtengo todos los turnos de la BD
    public List<Turno> encontrarTodos() {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Turno t ORDER BY t.identificadorProgresivo", Turno.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    //aqui busco un turno por su id
    public Turno encontrarPorId(Long id) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    //aqui calculo el siguiente identificador progresivo tipo T0001, T0002, etc...
    public String obtenerSiguienteIdentificador() {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            String maxId = em.createQuery(
                    "SELECT MAX(t.identificadorProgresivo) FROM Turno t WHERE t.identificadorProgresivo LIKE 'T%'",
                    String.class).getSingleResult();

            if (maxId == null) return "T0001"; //aqui si no hay turnos empiezo en T0001

            int numero = Integer.parseInt(maxId.substring(1)) + 1;
            //aqui formateo el numero con 4 digitos: T0001, T0002, etc
            return String.format("T%04d", numero);

        } finally {
            em.close();
        }
    }

    //aqui guardo o actualizo un turno dentro de un transaction
    public Turno guardar(Turno turno) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            if (turno.getId() == null) {
                //aqui es un turno nuevo, uso persist
                em.persist(turno);
            } else {
                turno = em.merge(turno);
                //aqui el turno ya existe, uso merge para actualizarlo
            }
            em.getTransaction().commit();
            return turno;
        } finally {
            if (em.getTransaction().isActive()) {
                //aqui el turno ya existe, uso merge para actualizarlo
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}