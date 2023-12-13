package Services;

import Models.Communication.Comment;
import DAOs.CommentDB;
import DAOs.DAOControllers.Users.FacultyMemberDAO;
import Services.ServicesInterfaces.CommentService;
import java.util.List;
import java.util.Optional;

public class CommentHandler implements CommentService {

    FacultyMemberDAO fdao;
    
    private final CommentDB commentDB = new CommentDB();
    
    @Override
    public List<Comment> getAllComments() {
        return commentDB.getAllCommentsByFaculty(fdao.getFacultyMember(2));
    }
    
    @Override
    public Comment postComment(Comment comment) {
        return commentDB.insertComment(comment) ? comment : null;
    }
    
    @Override
    public Optional<Comment> getCommentById(int commentId) {
        return Optional.ofNullable(commentDB.getComment(commentId));
    }
}
