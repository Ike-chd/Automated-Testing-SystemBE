package Services.ServicesInterfaces;

import java.util.Optional;
import Models.Courses.Module;
import java.util.List;

public interface ModuleService {

    public Optional<Module> getModule(int moduleId);

    public boolean addModule(Module module);

    public boolean deleteModule(int moduleId);

    public boolean updateModule(Module module);
    
    public List<Module> getAllModules();
}
