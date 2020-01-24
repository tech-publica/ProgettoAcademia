package net.bitsrl.academia.persistence.repositories.abstractions;

import net.bitsrl.academia.model.Course;

import java.util.Collection;

public interface RepositoryCourse {
    Course create(Course toInsert);
    boolean delete(int courseId);
    boolean update(int courseId, Course toUpdate);
    Collection<Course> getAll();
    Collection<Course> getByTitleLike(String pattern);
}
