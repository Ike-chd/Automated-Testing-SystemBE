package Services;

import DAOs.CommentDB;
import DAOs.DAOControllers.Communications.CommentDAO;
import Models.Communication.Comment;
import Models.Users.FacultyMember;
import Models.Users.Student;
import Services.ServicesInterfaces.CommentService;
import java.util.List;
import java.util.Optional;

public class CommentHandler implements CommentService {

    private CommentDAO cdao = new CommentDB();

    @Override
    public Optional<Comment> getComment(int id) {
        return Optional.ofNullable(cdao.getComment(id));
    }

    @Override
    public boolean insertComment(Comment comment) {
        return cdao.insertComment(comment);
    }

    @Override
    public boolean deleteComment(Comment comment) {
        return cdao.deleteComment(comment);
    }

    @Override
    public List<Comment> getAllStudentComments(Student student) {
        return cdao.getAllStudentComments(student);
    }

    @Override
    public List<Comment> getAllCommentsMadeByFacM(FacultyMember facultyMember) {
    return cdao.getAllCommentsByFaculty(facultyMember);
    }

}
