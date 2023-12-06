package Services;

import Models.StudentFeedback.StudentFeedback;
import Services.ServicesInterfaces.StudentFeedbackService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentFeedbackHandler implements StudentFeedbackService {

    private static final Map<Integer, StudentFeedback> studentFeedbackDatabase = new HashMap<>();

    @Override
    public Optional<StudentFeedback> getStudentFeedback(int feedbackID) {
        return Optional.ofNullable(studentFeedbackDatabase.get(feedbackID));
    }

    @Override
    public boolean addStudentFeedback(StudentFeedback studentFeedback) {
        if (!studentFeedbackDatabase.containsKey(studentFeedback.getFeedbackID())) {
            studentFeedbackDatabase.put(studentFeedback.getFeedbackID(), studentFeedback);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudentFeedback(int feedbackID, StudentFeedback updatedStudentFeedback) {
        if (studentFeedbackDatabase.containsKey(feedbackID)) {
            studentFeedbackDatabase.put(feedbackID, updatedStudentFeedback);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudentFeedback(int feedbackID) {
        if (studentFeedbackDatabase.containsKey(feedbackID)) {
            studentFeedbackDatabase.remove(feedbackID);
            return true;
        }
        return false;
    }
}
