package net.bitsrl.academia;

import net.bitsrl.academia.controller.InterfacciaUtente;
import net.bitsrl.academia.persistence.repositories.DataException;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryAgent;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryCourse;
import net.bitsrl.academia.persistence.repositories.jpa.AgentRepositoryJpa;
import net.bitsrl.academia.persistence.repositories.jpa.CourseRepositoryJpa;
import net.bitsrl.academia.persistence.repositories.jpa.EntityManagerFactorySingleton;
import net.bitsrl.academia.persistence.services.abstractions.HRService;
import net.bitsrl.academia.persistence.services.jpa.HrServiceJpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {
        try {
            EntityManagerFactory emf = EntityManagerFactorySingleton.getInstance().getFactory();
            EntityManager em = emf.createEntityManager();
            RepositoryAgent ra = new AgentRepositoryJpa(em);
            RepositoryCourse rc = new CourseRepositoryJpa();
            HRService service = new HrServiceJpa(ra, em);
            InterfacciaUtente mydbc = new InterfacciaUtente(service);
            mydbc.start();
            mydbc.close();
        } catch (DataException de) {
            System.out.println("stiamo sperimentando difficolta' tecniche...");
            //de.getCause().printStackTrace();
        }

    }
}
