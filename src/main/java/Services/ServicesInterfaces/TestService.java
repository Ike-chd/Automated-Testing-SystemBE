package Services.ServicesInterfaces;

import Models.Tests.Test;
import java.util.List;
import java.util.Optional;

public interface TestService {

    public Optional<Test> getTest(int TestID);

    public boolean insertTest(Test test);

    public double getTotalWeight(int moduleId);

    public boolean deleteTest(int testId);

    public boolean updateTest(Test test);

    public List<Test> getAllTests();
    
    public List<Test> getAllTestsByModule(int id);

    int getTotalTestMarks(int testId);
    
    public List<Test> allTestsInACourse(int courseId, int stuID);
    
    public List<Test> allTestsInACourse(int courseId);
}