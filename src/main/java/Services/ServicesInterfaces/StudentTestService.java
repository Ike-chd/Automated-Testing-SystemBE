package Services.ServicesInterfaces;

import Models.StudentTest.StudentTest;

import java.util.Optional;

public interface StudentTestService {

    Optional<StudentTest> getStudentTest(int testID);

    boolean addStudentTest(StudentTest studentTest);

    boolean updateStudentTest(int testID, StudentTest studentTest);

    boolean deleteStudentTest(int testID);
}
