package Models.Reports;

import Models.Courses.Topic;
import Models.Tests.Test;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Report {
    private Map<Topic, Integer> topics;
    private Topic[] hardestTopics = new Topic[5];
    private Map<Test, Double> testMarks = new HashMap<>();
    //private Map<Test, TestAttempt> attemps = new HashMap<>();
}
