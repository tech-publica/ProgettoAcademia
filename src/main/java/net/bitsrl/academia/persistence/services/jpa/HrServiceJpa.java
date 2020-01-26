package net.bitsrl.academia.persistence.services.jpa;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.DataException;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryAgent;
import net.bitsrl.academia.persistence.repositories.jpa.EntityManagerFactoryHolder;
import net.bitsrl.academia.persistence.services.abstractions.HRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Collection;

@Service
public class HrServiceJpa implements HRService {
    private RepositoryAgent agentRepo;
    private EntityManager em;

    @Autowired
    public HrServiceJpa(RepositoryAgent repo, EntityManagerFactoryHolder emHolder) {
        this.agentRepo = repo;
        this.em = emHolder.getCurrentManger();
    }
    @Override
    public Agent createAgent(Agent toInsert) throws DataException {
        em.getTransaction().begin();
        try {
            Agent inserted = agentRepo.create(toInsert);
            em.getTransaction().commit();
            return inserted;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new DataException("errore della creazione dell' agent con JPA", e);
        }
    }

    @Override
    public boolean deleteAgent(int agentId) throws DataException {
        em.getTransaction().begin();
        try {
            agentRepo.delete(agentId);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella cancellazione dell' agent con JPA", pe);
        }
    }

    @Override
    public boolean updateAgent(int agentId, Agent toUpdate) throws DataException {
        em.getTransaction().begin();
        try {
            agentRepo.update(agentId, toUpdate);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella cancellazione dell' agent con JPA", pe);
        }
    }

    @Override
    public Collection<Agent> getAllAgents() throws DataException {
        try {
            return agentRepo.getAll();
        } catch (PersistenceException pe) {
            throw new DataException("errore nella lista dell' agent con JPA", pe);
        }
    }

    @Override
    public Collection<Agent> getAgentsByLastNameLike(String pattern) throws DataException {
        try {
            return agentRepo.getByLastnameLike(pattern);
        } catch (PersistenceException pe) {
            throw new DataException("errore nella lista like su lastname dell' agent con JPA", pe);
        }
    }

    @Override
    public Course createCourse(Course toInsert) {
        return null;
    }

    @Override
    public boolean deleteCourse(int courseId) {
        return false;
    }

    @Override
    public boolean updateCourse(int courseId, Course toUpdate) {
        return false;
    }

    @Override
    public Collection<Course> getAllCourses() {
        return null;
    }

    @Override
    public Collection<Course> getCoursesByTitleLike(String pattern) {
        return null;
    }
}
