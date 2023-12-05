package Models.Courses;

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
}
