package Services;

import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.ModuleDB;
import Services.ServicesInterfaces.ModuleService;
import java.util.Optional;
import Models.Courses.Module;
import java.util.List;

public class ModuleHandler implements ModuleService {

    private ModuleDAO mdao = new ModuleDB();

    @Override
    public Optional<Module> getModule(int moduleId) {
        return Optional.ofNullable(mdao.getModule(moduleId));
    }

    @Override
    public boolean addModule(Module module) {
        if (mdao.insertModule(module)) {
            if (!module.getModules().isEmpty()) {
                for (Module mod : module.getModules()) {
                    mdao.addPrereqModule(mdao.getLastPrimaryKey(), mdao.getModuleIdByName(mod.getModuleName()));
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteModule(int moduleId) {
        return mdao.deleteModule(moduleId);
    }

    @Override
    public boolean updateModule(Module module) {
        return mdao.updateModule(module);
    }

    @Override
    public List<Module> getAllModules() {
        List<Module> allModules = mdao.allModules();
        for (Module module :  allModules) {
            module.setModules(mdao.getAllPrereqModulesByModuleID(module.getModuleID()));
        }
        return allModules;
    }
    
    @Override
    public List<Module> getAllModulesByCourse(int id) {
        List<Module> allModules = mdao.getAllModulesInACourse(id);
        for (Module module :  allModules) {
            module.setModules(mdao.getAllPrereqModulesByModuleID(module.getModuleID()));
        }
        return allModules;
    }
}

