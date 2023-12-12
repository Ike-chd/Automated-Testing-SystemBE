package Models.Courses;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Module {

    private int moduleID;
    private String moduleName;
    private String moduleDescription;
    private Map<Module, List<Module>> modules = new HashMap<>();

    public Module(int moduleID, String moduleName, String moduleDescription) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
    }
}
