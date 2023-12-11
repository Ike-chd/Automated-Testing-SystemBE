package Services;

import DAOs.DAOControllers.Tests.TestAttemptDAO;
import Models.Tests.TestAttempt;
import Services.ServicesInterfaces.TestAttemptService;
import java.util.Optional;

public class TestAttemptHandler implements TestAttemptService {

    TestAttemptDAO tadao;

    @Override
    public Optional<TestAttempt> getTestAttempt(int attemptID) {
        return Optional.ofNullable(tadao.getTestAttempt(attemptID));
    }

    @Override
    public boolean addTestAttempt(TestAttempt testAttempt) {
        return tadao.addTestAttempt(testAttempt);
    }
}
