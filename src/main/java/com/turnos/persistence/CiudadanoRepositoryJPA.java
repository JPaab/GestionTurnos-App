package com.turnos.persistence;

import com.turnos.entities.Ciudadano;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

public class CiudadanoRepositoryJPA {

    public Ciudadano buscarPorDni(String dni) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Ciudadano c WHERE c.dni = :dni", Ciudadano.class)
                    .setParameter("dni", dni)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Ciudadano> encontrarTodos() {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Ciudadano c ORDER BY c.apellido, c.nombre", Ciudadano.class)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public Ciudadano guardar(Ciudadano ciudadano) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            if (ciudadano.getId() == null) {
                em.persist(ciudadano);
            } else {
                ciudadano = em.merge(ciudadano);
            }
            em.getTransaction().commit();
            return ciudadano;
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
