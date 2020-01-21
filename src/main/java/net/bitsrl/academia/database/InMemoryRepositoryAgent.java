package net.bitsrl.academia.database;

import net.bitsrl.academia.model.Agent;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryRepositoryAgent implements RepositoryAgent {
    private DataBaseInMemory data = DataBaseInMemory.getInstance();


    @Override
    public Agent create(Agent toInsert) {
        Map<Integer, Agent> agents = data.getAgent();
        agents.put(toInsert.getId(), toInsert);
        return toInsert;
    }

    @Override
    public boolean delete(int agentId) {
        Map<Integer, Agent> agents = data.getAgent();
        Agent x = agents.remove(agentId);
        return x != null;
    }

    @Override
    public boolean update(int agentId, Agent toUpdate) {
        Map<Integer, Agent> agents = data.getAgent();
        Agent old = agents.replace(agentId, toUpdate);
        return old != null;
    }

    @Override
    public Collection<Agent> getAll() {
        Map<Integer, Agent> agents = data.getAgent();
        return agents.values();
    }

    @Override
    public Collection<Agent> getByLastnameLike(String pattern) {
        return data.getAgent().values().stream()
                .filter(a -> a.getLastname().contains(pattern))
                .collect(Collectors.toList());
    }
}
