package Models.Communication;

import Models.Users.FacultyMember;
import Models.Users.Student;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Comment {

    private int commentID;
    private int studentID;
    private int userID;
    private String comment;
    private Date commentDate;
    private Student student;
    private FacultyMember faculty;

    public Comment(int commentId, String comment, Student student, FacultyMember facultyMember) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
