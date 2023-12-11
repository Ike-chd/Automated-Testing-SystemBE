package DAOs.DAOControllers.Tests;

import Models.Tests.Test;
import Models.Tests.TestAttempt;
import Models.Users.Student;
import java.util.List;

public interface TestAttemptDAO {
    public TestAttempt getTestAttempt(int id);
    public boolean addTestAttempt(TestAttempt testAttempt);
    public List<TestAttempt> getAllTestAttemptsByTest(Test test);
    public List<TestAttempt> getAllTestAttemptsByStudent(Student student);
    public List<TestAttempt> getAllTestAttempts();
}