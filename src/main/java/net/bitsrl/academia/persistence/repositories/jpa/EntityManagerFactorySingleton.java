package net.bitsrl.academia.persistence.repositories.jpa;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerFactorySingleton {
    private static EntityManagerFactorySingleton
        instance = new EntityManagerFactorySingleton();
    private EntityManagerFactory emFactory =
            Persistence.createEntityManagerFactory("NewPersistenceUnit");

    public static EntityManagerFactorySingleton getInstance() {
        return instance;
    }
    private EntityManagerFactorySingleton() {

    }

    public EntityManagerFactory getFactory() {
        return emFactory;
    }

}
