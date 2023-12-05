package Services;

import Models.StudentTest.StudentTest;
import Services.ServicesInterfaces.StudentTestService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentTestHandler implements StudentTestService {

    private static final Map<Integer, StudentTest> studentTestDatabase = new HashMap<>();

    @Override
    public Optional<StudentTest> getStudentTest(int testID) {
        return Optional.ofNullable(studentTestDatabase.get(testID));
    }

    @Override
    public boolean addStudentTest(StudentTest studentTest) {
        if (!studentTestDatabase.containsKey(studentTest.getTestID())) {
            studentTestDatabase.put(studentTest.getTestID(), studentTest);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudentTest(int testID, StudentTest updatedStudentTest) {
        if (studentTestDatabase.containsKey(testID)) {
            studentTestDatabase.put(testID, updatedStudentTest);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudentTest(int testID) {
        if (studentTestDatabase.containsKey(testID)) {
            studentTestDatabase.remove(testID);
            return true;
        }
        return false;
    }
}
