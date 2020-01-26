package net.bitsrl.academia.persistence.repositories.jpa;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.persistence.repositories.DataException;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public class AgentRepositoryJpa implements RepositoryAgent {

    private EntityManager em;

    @Autowired
    public AgentRepositoryJpa(EntityManagerFactoryHolder holder) {
        this.em = holder.getCurrentManger();
    }


    @Override
    public Agent create(Agent toInsert) throws DataException {
        em.persist(toInsert);
        return toInsert;
    }

    @Override
    public boolean delete(int agentId) {
        Agent toDelete = em.getReference(Agent.class, agentId);
        em.remove(toDelete);
        return true;
    }

    @Override
    public boolean update(int agentId, Agent toUpdate) {
        Agent updated = em.merge(toUpdate);
        return true;
    }

    @Override
    public Collection<Agent> getAll() {
        return em.createQuery("select a from Agent a", Agent.class)
                .getResultList();
    }

    @Override
    public Collection<Agent> getByLastnameLike(String pattern) {
        TypedQuery q = em.createQuery("select a from Agent a where a.lastname LIKE :pattern", Agent.class);
        q.setParameter("pattern", "%" + pattern + "%");
        return q.getResultList();
    }
}
