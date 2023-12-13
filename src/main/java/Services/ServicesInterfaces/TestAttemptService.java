package Services.ServicesInterfaces;

import Models.Tests.Test;
import java.util.Optional;

public interface TestAttemptService {
    
    Optional<Test> getTestAttempt(int attemptID);

    boolean addTestAttempt(Test test);

    boolean updateTestAttempt(int attemptID, Test test);

    boolean deleteTestAttempt(int attemptID);
}
