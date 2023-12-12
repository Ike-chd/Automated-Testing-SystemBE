package Services;

import DAOs.DAOControllers.Users.StudentDAO;
import DAOs.StudentDB;
import Models.QA.Question;
import Models.Tests.Test;
import Models.Users.Student;
import Services.ServicesInterfaces.StudentService;
import java.util.List;
import java.util.Optional;

public class StudentHandler implements StudentService {
    StudentDAO sdao = new StudentDB();

    @Override
    public void viewReport() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void viewHardestTopic() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void viewTestMarks() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void takeTest(Test test) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void bookmarkQuestion(Question question) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void provideTestRating(Test test, double rating) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Student> getStudent(int id) {
        return Optional.ofNullable(sdao.getStudent(id));
    }

    @Override
    public boolean insertStudent(Student student) {
        return sdao.insertStudent(student);
    }

    @Override
    public boolean deleteStudent(int id) {
        return sdao.deleteStudent(id);
    }

    @Override
    public boolean updateStudent(Student student) {
        return sdao.updateStudent(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return sdao.getAllStudents();
    }

}