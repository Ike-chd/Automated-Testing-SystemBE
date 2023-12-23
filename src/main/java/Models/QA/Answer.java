package Models.QA;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Answer {

    private int answerID;
    private String answer;
    private boolean isCorrect;
    private Question question;
    
    public void setIsCorrect(boolean isCorrect){
        this.isCorrect = isCorrect;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public Answer() {
    }
    
}