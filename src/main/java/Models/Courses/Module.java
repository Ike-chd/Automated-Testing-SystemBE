package Models.Courses;

import Models.Tests.Test;
import java.util.ArrayList;
import java.util.List;
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
    private List<Test> tests = new ArrayList<>();
}
