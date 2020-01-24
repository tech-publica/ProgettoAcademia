package net.bitsrl.academia.persistence.repositories.inMemory;

import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryCourse;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

public class InMemoryRepositoryCourse implements RepositoryCourse {
    private DataBaseInMemory data = DataBaseInMemory.getInstance();

    @Override
    public Course create(Course toInsert) {
        Map<Integer, Course> Courses = data.getCourse();
        Courses.put(toInsert.getId(), toInsert);
        return toInsert;
    }

    @Override
    public boolean delete(int CourseId) {
        Map<Integer, Course> Courses = data.getCourse();
        Course x = Courses.remove(CourseId);
        return x != null;
    }

    @Override
    public boolean update(int CourseId, Course toUpdate) {
        Map<Integer, Course> Courses = data.getCourse();
        Course old = Courses.replace(CourseId, toUpdate);
        return old != null;
    }

    @Override
    public Collection<Course> getAll() {
        Map<Integer, Course> Courses = data.getCourse();
        return Courses.values();
    }

    @Override
    public Collection<Course> getByTitleLike(String pattern) {
        return data.getCourse().values().stream().filter
                (a -> a.getTitle().contains(pattern)).collect(Collectors.toList());
    }
}
