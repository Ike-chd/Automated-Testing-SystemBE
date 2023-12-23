package DAOs.DAOControllers.Courses;

import java.util.List;
import Models.Courses.Module;

public interface ModuleDAO {

    public Module getModule(int moduleId);

    public boolean insertModule(Module module);

    public boolean deleteModule(int module);

    public boolean updateModule(Module module);

    public List<Module> allModules();
    
    public int getModuleIdByName(String moduleName);
    
    public boolean addPrereqModule(int module1, int module2);
    
    public int getLastPrimaryKey();

    public List<Module> getAllPrereqModulesByModuleID(int moduleId);
    
    public List<Module> getAllModulesInACourse(int moduleId);

    public boolean insertModuleIntoCourse(int courseID, int moduleID);
}