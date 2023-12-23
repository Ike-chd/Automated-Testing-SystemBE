package Models.Communication;

import Models.Users.FacultyMember;
import Models.Users.Student;
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
    private String comment;
    private long commentDate;
    private Student student;
    private FacultyMember faculty;

    public Comment(int commentId, String comment, Student student, FacultyMember facultyMember) {
        this.commentID = commentId;
        this.comment = comment;
        this.student = student;
        this.faculty = facultyMember;
    }

    public Comment(int commentId, String commentText, Student student, FacultyMember facultyMember, long commentDate) {
        this.commentID = commentId;
        this.comment = commentText;
        this.student = student;
        this.faculty = facultyMember;
        this.commentDate = commentDate;
    }

}
