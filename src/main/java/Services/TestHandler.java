package Services;

import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.TestDB;
import Models.Tests.Test;
import Services.ServicesInterfaces.TestService;

import java.util.Optional;

public class TestHandler implements TestService {
    private TestDAO tdao = new TestDB();
    @Override
    public Optional<Test> getTest(int TestID) {
        return Optional.ofNullable(tdao.getTest(TestID));
    }

    @Override
    public void insertTest(Test test) {

    }
}
