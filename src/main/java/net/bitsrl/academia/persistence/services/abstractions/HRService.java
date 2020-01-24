package net.bitsrl.academia.persistence.services.abstractions;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.DataException;

import java.util.Collection;

public interface HRService {
    Agent createAgent(Agent toInsert) throws DataException;
    boolean deleteAgent(int agentId) throws DataException;
    boolean updateAgent(int agentId, Agent toUpdate) throws DataException;
    Collection<Agent> getAllAgents() throws DataException;
    Collection<Agent> getAgentsByLastNameLike(String pattern) throws DataException;

    Course createCourse(Course toInsert);
    boolean deleteCourse(int courseId);
    boolean updateCourse(int courseId, Course toUpdate);
    Collection<Course> getAllCourses();
    Collection<Course> getCoursesByTitleLike(String pattern);


}
