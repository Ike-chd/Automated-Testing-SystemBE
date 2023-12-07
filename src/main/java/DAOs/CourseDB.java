package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.CourseDAO;
import DBConnection.DBConnection;
import Models.Courses.Course;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDB extends DBConnection implements CourseDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Statement s;

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
    public boolean deleteCourse(Course course) {
        int update = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Courses WHERE courseID = ?");
            ps.setInt(1, course.getCourseID());
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
            s = getConnection().createStatement();
            rs = s.executeQuery("SELECT * FROM Courses");
            while (rs.next()) {
                Course course = extractCourseFromResultSet(rs);
                courses.add(course);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
                try {
                    if(s != null){
                    s.close();
                } else if(rs != null){
                    rs.close();
                }
                } catch (SQLException e) {
                    e.printStackTrace();
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
}
