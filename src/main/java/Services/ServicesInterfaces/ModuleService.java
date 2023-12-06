package Services.ServicesInterfaces;

import java.util.Optional;
import Models.Courses.Module;

public interface ModuleService {

    public Optional<Module> getModule(int moduleId);

    public boolean addModule(Module module);

    public boolean deleteModule(Module module);

    public boolean updateModule(Module module);
}
