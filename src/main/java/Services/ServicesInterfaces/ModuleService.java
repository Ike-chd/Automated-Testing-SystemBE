package Services.ServicesInterfaces;

import java.util.Optional;
import Models.Courses.Module;

public interface ModuleService {

    public Optional<Module> getModule(int moduleId);

    public boolean addModule(Module module);

    public boolean deleteModule(int moduleId);

    public boolean updateModule(Module module);
}
