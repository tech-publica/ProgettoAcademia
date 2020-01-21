package net.bitsrl.academia.database;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.model.Course;
import java.util.HashMap;
import java.util.Map;

public class DataBaseInMemory {
    //creare due hashMap   - course (con chiave id)     - agent (con chiave id)
    private Map<Integer, Agent> agents = new HashMap<>();
    private Map<Integer, Course> courses = new HashMap<>();

    private static DataBaseInMemory instance = new DataBaseInMemory();
    public static DataBaseInMemory getInstance(){
        return instance;
    }

    private DataBaseInMemory(){
        Agent a1 = new Agent(1,"Mario","Rossi");
        Agent a2 = new Agent(2,"Laura","Bianchi");
        Agent a3 = new Agent(3,"Sabrina","Verdi");

        Course c1 = new Course(1,"Java Base",120);
        Course c2 = new Course(2,"Java Enterprise Edition",140);
        Course c3 = new Course(3,"Java for Dummies",280);

        agents.put(a1.getId(), a1);
        agents.put(a2.getId(), a2);
        agents.put(a3.getId(), a3);

        courses.put(c1.getId(), c1);
        courses.put(c2.getId(), c2);
        courses.put(c3.getId(), c3);
    }

    public Map<Integer, Agent> getAgent() {
        return agents;
    }

    public Map<Integer, Course> getCourse() {
        return courses;
    }
}
