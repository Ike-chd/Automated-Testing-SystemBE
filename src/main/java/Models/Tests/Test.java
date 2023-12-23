package Models.Tests;

import Models.Courses.Topic;
import Models.QA.Question;
import Models.Courses.Module;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Test {

    public Test(int testId, String testName){
        this.testID = testId;
        this.testName = testName;
    }
    private int testID;
    private String testName;
    private Module module;
    private long startDate;
    private long duration;
    private double weight;
    private List<Question> questions = new ArrayList<>();
    private List<Topic> topics = new ArrayList<>();

    public Test(int testID, String testName, Module module, long startDate, long duration, double weight) {
        this.testID = testID;
        this.testName = testName;
        this.module = module;
        this.startDate = startDate;
        this.duration = duration;
        this.weight = weight;
    } 
}
