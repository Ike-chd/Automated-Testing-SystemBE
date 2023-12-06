package DAOs.DAOControllers.Courses;

import java.util.List;
import Models.Courses.Module;

public interface ModuleDAO {

    public Module getModule(int moduleId);

    public boolean insertModule(Module module);

    public boolean deleteModule(Module module);

    public boolean updateModule(Module module);

    public List<Module> allModules();
}
