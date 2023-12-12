
package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.CourseDAO;
import DAOs.DAOControllers.Users.AccessRoleDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.Courses.Course;
import Models.Users.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDB extends DBConnection implements StudentDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private CourseDAO cdao = new CourseDB();
    private AccessRoleDAO adao = new AccessRoleDB();

    @Override
    public Student getStudent(int id) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Students WHERE studentID = ?");
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
            ps = getConnection().prepareStatement("SELECT * FROM Students WHERE studentNum = ?");
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
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Students WHERE courseID = ?");
            ps.setInt(1,course.getCourseID());
            rs = ps.executeQuery();
            while(rs.next()) {
                students.add(extractStudentFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                CloseStreams.closeRsPs(rs,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return students;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Students");
            rs = ps.executeQuery();
            while(rs.next()){
                students.add(extractStudentFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return students;
    }

    @Override
    public boolean insertStudent(Student student) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Students (firstname, surname, email, address, idNumber, courseID, password, phoneNumber) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getIdNumber());
            ps.setInt(6, student.getCurrentCourse().getCourseID());
            ps.setString(7, student.getPassword());
            ps.setString(8,student.getPhoneNumber());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public boolean deleteStudent(int id) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM students WHERE studentID = ?");
            ps.setInt(1, id);
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
              ex.printStackTrace();
            }
        }
        return affectedRows == 1 ;
    }

    @Override
    public boolean updateStudent(Student student) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Students SET firstname = ?, surname = ?, email = ?, address = ?, idNumber = ?, courseID = ?, password = ?, phoneNumber = ? WHERE studentID = ?");
            ps.setString(1, student.getName());
            ps.setString(2, student.getSurname());
            ps.setString(3, student.getEmail());
            ps.setString(4, student.getAddress());
            ps.setString(5, student.getIdNumber());
            ps.setInt(6, student.getCurrentCourse().getCourseID());
            ps.setString(7, student.getPassword());
            ps.setString(8, student.getPhoneNumber());
            ps.setInt(9, student.getUserID());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
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
        String phoneNumber = resultSet.getString("phoneNumber");
        int acceessRole = resultSet.getInt("accessRoleID");
       return new Student(phoneNumber,address,cdao.getCourse(courseID),studentID,name,surname,email,idNumber,password, adao.getAccessRole(acceessRole));
    }

}
