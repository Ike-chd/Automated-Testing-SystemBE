package Services.ServicesInterfaces;

import Models.StudentPerformance.StudentPerformance;

import java.util.Optional;

public interface StudentPerformanceService {

    Optional<StudentPerformance> getStudentPerformance(int performanceID);

    boolean addStudentPerformance(StudentPerformance studentPerformance);

    boolean updateStudentPerformance(int performanceID, StudentPerformance studentPerformance);

    boolean deleteStudentPerformance(int performanceID);
}
