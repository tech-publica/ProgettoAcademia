package net.bitsrl.academia.database;

import net.bitsrl.academia.model.Agent;
import java.util.Collection;

public interface RepositoryAgent {
    Agent create(Agent toInsert);
    boolean delete(int agentId);
    boolean update(int agentId, Agent toUpdate);
    Collection<Agent> getAll();
    Collection<Agent> getByLastnameLike(String pattern);
    //aggiungere metodi
}
