package net.bitsrl.academia.persistence.repositories.jpa;

import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryCourse;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class CourseRepositoryJpa implements RepositoryCourse {
    @Override
    public Course create(Course toInsert) {
        return null;
    }

    @Override
    public boolean delete(int courseId) {
        return false;
    }

    @Override
    public boolean update(int courseId, Course toUpdate) {
        return false;
    }

    @Override
    public Collection<Course> getAll() {
        return null;
    }

    @Override
    public Collection<Course> getByTitleLike(String pattern) {
        return null;
    }
}
