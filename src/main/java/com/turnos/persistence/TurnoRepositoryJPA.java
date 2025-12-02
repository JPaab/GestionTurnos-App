package com.turnos.persistence;

import com.turnos.entities.Turno;
import jakarta.persistence.EntityManager;

import java.util.List;

public class TurnoRepositoryJPA {

    public List<Turno> encontrarTodos() {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.createQuery("SELECT t FROM Turno t", Turno.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Turno encontrarPorId(Long id) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.find(Turno.class, id);
        } finally {
            em.close();
        }
    }

    public String obtenerSiguienteIdentificador() {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            String maxId = em.createQuery(
                    "SELECT MAX(t.identificadorProgresivo) FROM Turno t WHERE t.identificadorProgresivo LIKE 'T%'",
                    String.class).getSingleResult();

            if (maxId == null) return "T0001";

            int numero = Integer.parseInt(maxId.substring(1)) + 1;
            return String.format("T%04d", numero);

        } finally {
            em.close();
        }
    }

    public Turno guardar(Turno turno) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            if (turno.getId() == null) {
                em.persist(turno);
            } else {
                turno = em.merge(turno);
            }
            em.getTransaction().commit();
            return turno;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}