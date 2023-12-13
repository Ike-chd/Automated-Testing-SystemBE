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
        return mdao.insertModule(module);
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
        return mdao.allModules();
    }
}
