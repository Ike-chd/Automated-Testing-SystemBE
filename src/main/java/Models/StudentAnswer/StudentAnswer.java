package Models.StudentAnswer;

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
    private int studentID;
    private int questionID;
    private boolean correctAns;
    private int testID;
}
