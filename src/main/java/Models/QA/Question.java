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
    private final List<Answer> answers = new ArrayList<>();
}