package net.bitsrl.academia.database;

import net.bitsrl.academia.model.ConnectionFactory;
import net.bitsrl.academia.model.Course;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class InDbRepositoryCourse implements RepositoryCourse{

    final String CREATE = "INSERT INTO course (title, numHours) VALUES (?, ?);";
    final String DELETE = "DELETE FROM course WHERE id = ?;";
    final String UPDATE = "UPDATE course SET title = ?, numHours = ? WHERE (id = ?);";
    final String GET_ALL = "SELECT id, title, numHours FROM course;";
    final String GET_BY_TITLE_LIKE = "SELECT id, title, numHours FROM course WHERE title LIKE ?;";


    @Override
    public Course create(Course toInsert) {
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(CREATE)) {
            st.setString(1, toInsert.getTitle());
            st.setInt(2, toInsert.getDurHour());
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return toInsert;
    }

    @Override
    public boolean delete(int courseId) {
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(DELETE)) {
            st.setString(1, String.valueOf(courseId));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public boolean update(int courseId, Course toUpdate) {
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(UPDATE)) {
            st.setString(1, toUpdate.getTitle());
            st.setString(2, String.valueOf(toUpdate.getDurHour()));
            st.setString(3, String.valueOf(courseId));
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public Collection<Course> getAll() {
        Collection<Course> courses = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection();
             Statement st = con.createStatement()) {
            ResultSet rs = st.executeQuery(GET_ALL);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int durHour = rs.getInt("numHours");
                courses.add(new Course(id, title, durHour));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public Collection<Course> getByTitleLike(String pattern) {
        Collection<Course> courses = new ArrayList<>();
        try (Connection con = ConnectionFactory.createConnection();
             PreparedStatement st = con.prepareStatement(GET_BY_TITLE_LIKE)) {
            st.setString(1, "%" + pattern + "%");
            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String title = rs.getString("title");
                    int durHour = rs.getInt("numHours");
                    courses.add(new Course(id, title, durHour));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}

