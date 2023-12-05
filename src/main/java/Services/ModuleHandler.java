package Services;

import DAOs.DAOControllers.Courses.ModuleDAO;
import DAOs.ModuleDB;
import Services.ServicesInterfaces.ModuleService;
import java.util.Optional;
import Models.Courses.Module;

public class ModuleHandler implements ModuleService {

    private ModuleDAO mdao = new ModuleDB();

    @Override
    public Optional<Module> getModule(int moduleId) {
        return Optional.ofNullable(mdao.getModule(moduleId));
    }

    @Override
    public boolean addModule(int moduleId) {
        return mdao.insertModule(moduleId);
    }

    @Override
    public boolean deleteModule(int moduleId) {
        return mdao.deleteModule(moduleId);
    }

    @Override
    public boolean updateModule(int moduleId) {
        return mdao.updateModule(moduleId);
    }
}
