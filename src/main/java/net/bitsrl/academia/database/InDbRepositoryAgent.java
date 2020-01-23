package net.bitsrl.academia.database;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.model.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class InDbRepositoryAgent implements RepositoryAgent {
    private DataBaseInMemory data = DataBaseInMemory.getInstance();
    //    String path = getClass().getClassLoader().getResource("systemRepositoryAgent").getPath();
    String path = "src/main/resources/systemRepositoryAgent";
    final String CREATE = "INSERT INTO agent (firstname, lastname) VALUES (?, ?);";
    final String DELETE = "DELETE FROM agent WHERE id = ?;";
    final String UPDATE = "UPDATE agent SET firstname = ?, lastname = ? WHERE (id = ?);";
    final String GET_ALL = "SELECT id, firstname, lastname from agent;";
    final String GET_BY_LASTNAME_LIKE = "SELECT id, firstname, lastname from agent WHERE lastname LIKE ?;";

    @Override
    public Agent create(Agent toInsert) {
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(CREATE)) {
            st.setString(1, toInsert.getFirstname());
            st.setString(2, toInsert.getLastname());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toInsert;
    }

    @Override
    public boolean delete(int agentId) {
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(DELETE)) {
            st.setString(1, String.valueOf(agentId));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(int agentId, Agent toUpdate) {
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(UPDATE)) {
            st.setString(1, toUpdate.getFirstname());
            st.setString(2, toUpdate.getLastname());
            st.setString(3, String.valueOf(agentId));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public Collection<Agent> getAll() {
        Collection<Agent> agents = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(GET_ALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                agents.add(new Agent(id, firstname, lastname));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agents;
    }

    @Override
    public Collection<Agent> getByLastnameLike(String pattern) {
        Collection<Agent> agents = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(GET_BY_LASTNAME_LIKE)) {
            st.setString(1, "%" + pattern + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String firstname = rs.getString("firstname");
                    String lastname = rs.getString("lastname");
                    agents.add(new Agent(id, firstname, lastname));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return agents;
    }
}
