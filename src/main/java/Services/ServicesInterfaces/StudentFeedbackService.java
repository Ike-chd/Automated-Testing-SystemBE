package Services.ServicesInterfaces;

import Models.StudentFeedback.StudentFeedback;

import java.util.Optional;

public interface StudentFeedbackService {

    Optional<StudentFeedback> getStudentFeedback(int feedbackID);

    boolean addStudentFeedback(StudentFeedback studentFeedback);

    boolean updateStudentFeedback(int feedbackID, StudentFeedback studentFeedback);

    boolean deleteStudentFeedback(int feedbackID);
}
