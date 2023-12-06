package DAOs.DAOControllers.Users;

import Models.Courses.Course;
import Models.Users.Student;
import java.util.List;

public interface StudentDAO {

    public Student getStudent(int id);

    public Student getStudent(String stID);

    public List<Student> getStudentsByCourse(Course course);

    public List<Student> getAllStudents();

    public boolean insertStudent(Student student);

    public boolean deleteStudent(int id);

    public boolean updateStudent(Student student);
}
