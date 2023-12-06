package DAOs.DAOControllers.Communications;

import Models.Communication.Comment;
import Models.Users.FacultyMember;
import Models.Users.Student;
import java.util.List;

public interface CommentDAO {

    public Comment getComment(int commentId);

    public boolean insertComment(Comment commnet);

    public boolean updateComment(Comment comment);

    public boolean deleteComment(Comment comment);

    public List<Comment> getAllStudentComments(Student student);

    public List<Comment> getAllCommentsByFaculty(FacultyMember faculty);
}
