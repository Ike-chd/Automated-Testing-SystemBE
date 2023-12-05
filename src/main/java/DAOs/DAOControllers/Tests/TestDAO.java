package DAOs.DAOControllers.Tests;

import Models.Courses.Topic;
import Models.Tests.Test;
import Models.Courses.Module;
import java.util.List;

public interface TestDAO {

    public Test getTest(int id);

    public boolean insetTest(Test test);

    public boolean deleteTest(Test test);

    public boolean updateTest(Test test);

    public List<Test> allModuleTests(Module module);

    List<Test> getAllTests();
}
