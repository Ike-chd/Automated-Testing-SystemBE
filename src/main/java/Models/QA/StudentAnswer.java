package Models.QA;

import Models.Tests.Test;
import Models.Users.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentAnswer {

    private int qaId;
    private Student student;
    private Question question;
    private boolean correctAns;
    private Test test;
}
