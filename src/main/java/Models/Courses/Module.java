package Models.Courses;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Module {

    private int moduleID;
    private String moduleName;
    private String moduleDescription;
    private List<Module> modules = new ArrayList<>();
    
    public Module(int moduleID, String moduleName, String moduleDescription) {
        this.moduleID = moduleID;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
    }
}
