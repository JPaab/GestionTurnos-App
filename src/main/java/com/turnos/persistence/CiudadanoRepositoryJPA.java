package com.turnos.persistence;

import com.turnos.entities.Ciudadano;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

import java.util.List;

//aqui repositorio JPA para hacer las consultas de Ciudadano contra la BD
public class CiudadanoRepositoryJPA {

    //aqui busco un ciudadano por su dni usando JPQL
    public Ciudadano buscarPorDni(String dni) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.createQuery("SELECT c FROM Ciudadano c WHERE c.dni = :dni", Ciudadano.class)
                    .setParameter("dni", dni)
                    .getSingleResult();
        } catch (NoResultException e) {
            //aqui si no encuentra nada devuelvo null para poder controlarlo en el servlet
            return null;
        } finally {
            em.close();
        }
    }

    //aqui obtengo la lista completa de ciudadanos ordenados por id
    public List<Ciudadano> encontrarTodos() {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            return em.createQuery(
                    "SELECT c FROM Ciudadano c ORDER BY c.id",
                    Ciudadano.class
            ).getResultList();
        } finally {
            em.close();
        }
    }

    //aqui guardo o actualizo un ciudadano usando una transaccion JPA
    public Ciudadano guardar(Ciudadano ciudadano) {
        EntityManager em = ConfigJPA.getEntityManager();
        try {
            em.getTransaction().begin();
            if (ciudadano.getId() == null) {
                //aqui es un ciudadano nuevo, uso persist
                em.persist(ciudadano);
            } else {
                //aqui ya existe en BD, uso merge para actualizarlo
                ciudadano = em.merge(ciudadano);
            }
            em.getTransaction().commit();
            return ciudadano;
        } finally {
            if (em.getTransaction().isActive()) {
                //aqui hago rollback si algo salio mal
                em.getTransaction().rollback();
            }
            em.close();
        }
    }
}
