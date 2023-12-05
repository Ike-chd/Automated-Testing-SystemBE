package Services;

import Models.Tests.Test;
import Services.ServicesInterfaces.TestAttemptService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TestAttemptHandler implements TestAttemptService {

    private static final Map<Integer, Test> testAttemptDatabase = new HashMap<>();

    @Override
    public Optional<Test> getTestAttempt(int attemptID) {
        return Optional.ofNullable(testAttemptDatabase.get(attemptID));
    }

    @Override
    public boolean addTestAttempt(Test test) {
        if (!testAttemptDatabase.containsKey(test.getTestID())) {
            testAttemptDatabase.put(test.getTestID(), test);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateTestAttempt(int attemptID, Test updatedTest) {
        if (testAttemptDatabase.containsKey(attemptID)) {
            testAttemptDatabase.put(attemptID, updatedTest);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteTestAttempt(int attemptID) {
        if (testAttemptDatabase.containsKey(attemptID)) {
            testAttemptDatabase.remove(attemptID);
            return true;
        }
        return false;
    }
}
