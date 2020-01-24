package net.bitsrl.academia.persistence.repositories.abstractions;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.persistence.repositories.DataException;

import java.io.IOException;
import java.util.Collection;

public interface RepositoryAgent {
    Agent create(Agent toInsert) throws DataException;
    boolean delete(int agentId) throws DataException;
    boolean update(int agentId, Agent toUpdate) throws DataException;
    Collection<Agent> getAll() throws DataException;
    Collection<Agent> getByLastnameLike(String pattern) throws DataException;
}
