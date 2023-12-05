package Services;

import Models.StudentPerformance.StudentPerformance;
import Services.ServicesInterfaces.StudentPerformanceService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class StudentPerformanceHandler implements StudentPerformanceService {

    private static final Map<Integer, StudentPerformance> studentPerformanceDatabase = new HashMap<>();

    @Override
    public Optional<StudentPerformance> getStudentPerformance(int performanceID) {
        return Optional.ofNullable(studentPerformanceDatabase.get(performanceID));
    }

    @Override
    public boolean addStudentPerformance(StudentPerformance studentPerformance) {
        if (!studentPerformanceDatabase.containsKey(studentPerformance.getPerformanceID())) {
            studentPerformanceDatabase.put(studentPerformance.getPerformanceID(), studentPerformance);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateStudentPerformance(int performanceID, StudentPerformance updatedStudentPerformance) {
        if (studentPerformanceDatabase.containsKey(performanceID)) {
            studentPerformanceDatabase.put(performanceID, updatedStudentPerformance);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudentPerformance(int performanceID) {
        if (studentPerformanceDatabase.containsKey(performanceID)) {
            studentPerformanceDatabase.remove(performanceID);
            return true;
        }
        return false;
    }
}
