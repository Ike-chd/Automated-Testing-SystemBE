package Models.QA;

import Models.Courses.Topic;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Question {

    private int questionID;
    private String question;
    private int markAllocation;
    private Topic topic;
    private List<Answer> answers = new ArrayList<>();

    public Question(int questionID, String question, int markAllocation, Topic topic) {
        this.questionID = questionID;
        this.question = question;
        this.markAllocation = markAllocation;
        this.topic = topic;
    }

    public Question(String question, int markAllocation, Topic topic) {
        this.question = question;
        this.markAllocation = markAllocation;
        this.topic = topic;
    }
    
}