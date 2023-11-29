package Models.QA;

import Models.Courses.Topic;
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
}
