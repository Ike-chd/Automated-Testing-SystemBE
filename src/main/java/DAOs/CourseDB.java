package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.CourseDAO;
import DBConnection.DBConnection;
import Models.Courses.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDB extends DBConnection implements CourseDAO {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Course getCourse(int courseId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Courses WHERE courseID = ?");
            ps.setInt(1, courseId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractCourseFromResultSet(rs);
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean insertCourse(Course course) {
        int update = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Courses (courseName, courseNumber) VALUES (?, ?)");
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseNumber());
            update = ps.executeUpdate();
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update == 1;
    }

    @Override
    public boolean deleteCourse(int course) {
        int update = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Courses WHERE courseID = ?");
            ps.setInt(1, course);
            update = ps.executeUpdate();
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update == 1;
    }

    @Override
    public boolean updateCourse(Course course) {
        int update = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Courses SET courseName = ?, courseNumber = ? WHERE courseID = ?");
            ps.setString(1, course.getCourseName());
            ps.setString(2, course.getCourseNumber());
            ps.setInt(3, course.getCourseID());
            update = ps.executeUpdate();
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return update > 0;
    }

    @Override
    public List<Course> allCourses() {
        List<Course> courses = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Courses");
            rs = ps.executeQuery();
            while (rs.next()) {
                courses.add(extractCourseFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return courses;
    }

    private Course extractCourseFromResultSet(ResultSet resultSet) throws SQLException {
        int courseId = resultSet.getInt("courseID");
        String courseName = resultSet.getString("courseName");
        String courseNumber = resultSet.getString("courseNumber");
        return new Course(courseId, courseName, courseNumber);
    }

    @Override
    public void deleteAllModulesInCourse(int courseID) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM course_module WHERE course_id = ?");
            ps.setInt(1, courseID);
            ps.executeUpdate();
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
