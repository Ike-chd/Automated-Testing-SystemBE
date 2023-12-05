package DAOs.DAOControllers.Courses;

import java.util.List;
import Models.Courses.Module;

public interface ModuleDAO {

    public Module getModule(int moduleId);

    public boolean insertModule(int module);

    public boolean deleteModule(int module);

    public boolean updateModule(int module);

    public List<Module> allModules();
}
