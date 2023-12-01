package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.CourseDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.Courses.Course;
import Models.Users.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDB implements StudentDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private DBConnection connection;
    private CourseDAO cdao = new CourseDB();

    @Override
    public Student getStudent(int id) {
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM Students WHERE studentID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractStudentFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Student getStudent(String stID) {
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM Students WHERE studentNum = ?");
            ps.setString(1, stID);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractStudentFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Student> getStudentsByCourse(Course course) {
        List<Student> students = new ArrayList<>();
        return students;
    }

    @Override
    public List<Student> getAllStudents() {
        return null;
    }

    @Override
    public boolean insertStudent(Student student) {
        try {
            ps = connection.getConnection().prepareStatement("INSERT INTO Students (firstname, surname, email, address, idNumber, courseID, password, studentNum) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getIdNumber());
            ps.setInt(6, student.getCurrentCourse().getCourseID());
            ps.setString(7, student.getPassword());
            ps.setString(8, student.getUsername());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean deleteStudent(Student student) {
        try {
            ps = connection.getConnection().prepareStatement("DELETE FROM students WHERE studentID = ?");
            ps.setInt(1, student.getUserID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
              ex.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public boolean updateStudent(Student student) {
        try {
            ps = connection.getConnection().prepareStatement("UPDATE Students SET firstname = ?, surname = ?, email = ?, address = ?, idNumber = ?, courseID = ?, password = ?, studentNum = ? WHERE studentID = ?");
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getIdNumber());
            ps.setInt(6, student.getCurrentCourse().getCourseID());
            ps.setString(7, student.getPassword());
            ps.setString(8, student.getUsername());
            ps.setInt(9, student.getUserID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }

    private Student extractStudentFromResultSet(ResultSet resultSet) throws SQLException {
        int studentID = resultSet.getInt("studentID");
        String name = resultSet.getString("firstname");
        String surname = resultSet.getString("surname");
        String email = resultSet.getString("email");
        String address = resultSet.getString("address");
        String idNumber = resultSet.getString("idNumber");
        int courseID = resultSet.getInt("courseID");
        String password = resultSet.getString("password");
        String studentNum = resultSet.getString("studentNum");
//        return new Student(studentID,studentNum,name,surname,email,idNumber,address,password,1,cdao.getCourse(courseID));
        return null;
    }

}
