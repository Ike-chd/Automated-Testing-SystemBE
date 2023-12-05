package Services.ServicesInterfaces;

import Models.StudentAnswer.StudentAnswer;

import java.util.Optional;

public interface StudentAnswerService {

    Optional<StudentAnswer> getStudentAnswer(int qaID);

    boolean addStudentAnswer(StudentAnswer studentAnswer);

    boolean updateStudentAnswer(int qaID, StudentAnswer studentAnswer);

    boolean deleteStudentAnswer(int qaID);
}
