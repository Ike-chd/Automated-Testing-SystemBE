package Models.Report;

import Models.Courses.Topic;
import Models.Tests.Test;
import Models.Tests.testAttempt;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Report {

    Topic[] hardestTopics = new Topic[5];
    private Map<Test, Double> testMarks = new HashMap<>();
    private Map<Test, testAttempt> attemps = new HashMap<>();
}
