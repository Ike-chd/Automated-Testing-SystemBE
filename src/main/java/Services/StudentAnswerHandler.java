package Services;

import Models.StudentAnswer.StudentAnswer;
import Services.ServicesInterfaces.StudentAnswerService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentAnswerHandler implements StudentAnswerService {

    private static final Map<Integer, StudentAnswer> studentAnswerDatabase = new HashMap<>();

    @Override
    public Optional<StudentAnswer> getStudentAnswer(int qaID) {
        return Optional.ofNullable(studentAnswerDatabase.get(qaID));
    }

    @Override
    public boolean addStudentAnswer(StudentAnswer studentAnswer) {
        if (!studentAnswerDatabase.containsKey(studentAnswer.getQaID())) {
            studentAnswerDatabase.put(studentAnswer.getQaID(), studentAnswer);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudentAnswer(int qaID, StudentAnswer updatedStudentAnswer) {
        if (studentAnswerDatabase.containsKey(qaID)) {
            studentAnswerDatabase.put(qaID, updatedStudentAnswer);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudentAnswer(int qaID) {
        if (studentAnswerDatabase.containsKey(qaID)) {
            studentAnswerDatabase.remove(qaID);
            return true;
        }
        return false;
    }
}
