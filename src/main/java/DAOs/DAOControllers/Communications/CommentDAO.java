package DAOs.DAOControllers.Communications;

import Models.Communication.Comment;
import Models.Users.FacultyMember;
import Models.Users.Student;
import java.util.List;

public interface CommentDAO {

    public Comment getComment(int commentId);

    public boolean insertComment(Comment comment);

    public boolean updateComment(Comment comment);

    public boolean deleteComment(int comment);
    
    public List<Comment> getAllComments();

    public List<Comment> getAllStudentComments(int studentID);

    public List<Comment> getAllCommentsByFaculty(int facultyID);
}
