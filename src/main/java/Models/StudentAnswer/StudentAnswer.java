package Models.StudentAnswer;

import Models.QA.Question;
import Models.Tests.Test;
import Models.Users.Student;
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
public class StudentAnswer {

    private int qaID;
    private Student student;
    private Question question;
    private boolean correctAns;
    private Test test;
}
