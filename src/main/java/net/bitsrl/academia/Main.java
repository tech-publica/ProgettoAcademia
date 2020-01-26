package net.bitsrl.academia;

import net.bitsrl.academia.controller.InterfacciaUtente;
import net.bitsrl.academia.persistence.repositories.DataException;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryAgent;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryCourse;
import net.bitsrl.academia.persistence.repositories.jpa.AgentRepositoryJpa;
import net.bitsrl.academia.persistence.repositories.jpa.CourseRepositoryJpa;
import net.bitsrl.academia.persistence.repositories.jpa.EntityManagerFactoryHolder;
import net.bitsrl.academia.persistence.services.abstractions.HRService;
import net.bitsrl.academia.persistence.services.jpa.HrServiceJpa;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;

public class Main {
    public static void main(String[] args) {
        try {

            ApplicationContext appContext = new AnnotationConfigApplicationContext(AcademiaSpringConfig.class);

            /*
            EntityManagerFactory emf = EntityManagerFactoryHolder.getInstance().getFactory();
            EntityManager em = emf.createEntityManager();
            RepositoryAgent ra = new AgentRepositoryJpa(em);
            RepositoryCourse rc = new CourseRepositoryJpa();
            HRService service = new HrServiceJpa(ra, em);
            InterfacciaUtente mydbc = new InterfacciaUtente(service);
            */

            // thank you, Spring
            InterfacciaUtente mydbc = appContext.getBean(InterfacciaUtente.class);
            mydbc.start();
            mydbc.close();
        } catch (DataException de) {
            System.out.println("stiamo sperimentando difficolta' tecniche...");
            //de.getCause().printStackTrace();
        }

    }
}
