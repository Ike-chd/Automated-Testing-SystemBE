package Services.ServicesInterfaces;

import Models.Tests.Test;
import Models.Tests.TestAttempt;
import java.util.Optional;

public interface TestAttemptService {
    
    public Optional<TestAttempt> getTestAttempt(int attemptID);

    public boolean addTestAttempt(TestAttempt test);
}