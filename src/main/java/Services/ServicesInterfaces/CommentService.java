package Services.ServicesInterfaces;

import Models.Communication.Comment;
import Models.Users.FacultyMember;
import Models.Users.Student;
import java.util.List;
import java.util.Optional;

public interface CommentService {

    public Optional<Comment> getComment(int id);

    public boolean insertComment(Comment comment);
    
    public boolean deleteComment(Comment comment);
    
    public List<Comment> getAllStudentComments(Student student);
    
    public List<Comment> getAllCommentsMadeByFacM(FacultyMember facultyMember);
}
