package Services.ServicesInterfaces;

import java.util.Optional;
import Models.Courses.Module;

public interface ModuleService {

    public Optional<Module> getModule(int moduleId);

    public boolean addModule(int moduleId);

    public boolean deleteModule(int moduleId);

    public boolean updateModule(int moduleId);
}
