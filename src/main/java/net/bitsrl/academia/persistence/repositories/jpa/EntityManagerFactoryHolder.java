package net.bitsrl.academia.persistence.repositories.jpa;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Service
public class EntityManagerFactoryHolder {
    private static EntityManagerFactoryHolder
        instance = new EntityManagerFactoryHolder();
    private EntityManager currentManger;
    public EntityManagerFactoryHolder() {
        currentManger = emFactory.createEntityManager();
    }
    private EntityManagerFactory emFactory =
            Persistence.createEntityManagerFactory("NewPersistenceUnit");

    public static EntityManagerFactoryHolder getInstance() {
        return instance;
    }

    public EntityManagerFactory getFactory() {
        return emFactory;
    }

    public EntityManager getCurrentManger() {
        return currentManger;
    }

}
