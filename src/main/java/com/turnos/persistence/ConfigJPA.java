package com.turnos.persistence;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigJPA {
    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("TurnosPU");
        } catch (Throwable ex) {
            //aqui se muestra un error si no se puede crear el factory
            System.err.println("ERROR al crear EntityManagerFactory: " + ex);
            //aqui lanza una excepcion si falla el initializer
            throw new ExceptionInInitializerError(ex);
        }

    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void close() {
        emf.close();
    }
}
