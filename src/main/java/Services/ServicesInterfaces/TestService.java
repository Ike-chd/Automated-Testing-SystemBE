package Services.ServicesInterfaces;

import Models.Tests.Test;
import java.util.Optional;

public interface TestService {
    public Optional<Test> getTest(int TestID);
    public void insertTest(Test test);
}
