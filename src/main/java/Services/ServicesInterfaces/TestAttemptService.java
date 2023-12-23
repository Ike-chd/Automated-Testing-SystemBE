package Services.ServicesInterfaces;

import Models.Tests.TestAttempt;
import java.util.Optional;

public interface TestAttemptService {
    
    Optional<TestAttempt> getTestAttempt(int attemptID);

    boolean addTestAttempt(TestAttempt testAttempt);

    boolean updateTestAttempt(TestAttempt testAttempt);

    boolean deleteTestAttempt(int attemptID);
}
