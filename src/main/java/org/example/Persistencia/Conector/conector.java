package org.example.Persistencia.Conector;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class conector {
    private static  final EntityManagerFactory conector;
    static {
        EntityManagerFactory entityManagerFactory=null;

        try {
            entityManagerFactory= Persistence.createEntityManagerFactory("app");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
           conector=entityManagerFactory;
        }
    }

    public static EntityManager getEntityManager() {
        if (conector == null) {
            throw new IllegalStateException("EntityManagerFactory no inicializado.");
        }
        return conector.createEntityManager();
    }

    public static void close() {
        if (conector != null) {
            conector.close();
        }
    }
}
