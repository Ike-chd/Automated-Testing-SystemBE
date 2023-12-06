package DAOs.DAOControllers.QA;

import Models.QA.Answer;
import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Users.Student;

import java.util.List;
import java.util.Map;

public interface StudentAnswerDAO {

    StudentAnswer getStudentAnswer(int qaId);

    boolean insertStudentAnswer(StudentAnswer studentAnswer);

    boolean updateStudentAnswer(StudentAnswer studentAnswer);

    boolean deleteStudentAnswer(StudentAnswer studentAnswer);

    List<StudentAnswer> getStudentAnswers();

    List<StudentAnswer> getStudentAnswersByStudent(Student student);

    List<StudentAnswer> getStudentAnswersByQuestion(Question question);

    List<StudentAnswer> getStudentAnswersByTest(Test test);
}
