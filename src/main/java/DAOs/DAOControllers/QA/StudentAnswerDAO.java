package DAOs.DAOControllers.QA;

import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Users.Student;

import java.util.List;

public interface StudentAnswerDAO {

    StudentAnswer getStudentAnswer(int qaId);

    boolean insertStudentAnswer(StudentAnswer studentAnswer);

    boolean updateStudentAnswer(StudentAnswer studentAnswer);

    boolean deleteStudentAnswer(StudentAnswer studentAnswer);

    List<StudentAnswer> getStudentAnswers();

    List<StudentAnswer> getStudentAnswersByStudent(Student student);

    List<StudentAnswer> getStudentAnswersByQuestion(Question question);

    List<StudentAnswer> getStudentAnswersByTest(Test test);
    
    List<StudentAnswer> getStudentAnswersByQuestionAndStudent(Question question, Student student);
}
