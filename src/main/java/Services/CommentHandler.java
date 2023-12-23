package Services;

import Models.Communication.Comment;
import DAOs.CommentDB;
import DAOs.DAOControllers.Communications.CommentDAO;
import DAOs.DAOControllers.Users.FacultyMemberDAO;
import DAOs.FacultyMemberDB;
import Services.ServicesInterfaces.CommentService;
import java.util.List;
import java.util.Optional;

public class CommentHandler implements CommentService {
    
    private final CommentDAO cdao = new CommentDB();
    
    @Override
    public List<Comment> getAllComments() {
        return cdao.getAllComments();
    }
    
    @Override
    public boolean insertComment(Comment comment) {
        return cdao.insertComment(comment);
    }
    
    @Override
    public List<Comment> getAllCommentsForMe(int stuID){
        return cdao.getAllStudentComments(stuID);
    }
    
    @Override
    public List<Comment> getAllCommentsIMade(int FacID){
        return cdao.getAllCommentsByFaculty(FacID);
    }
    
    @Override
    public Optional<Comment> getCommentById(int commentId) {
        return Optional.ofNullable(cdao.getComment(commentId));
    }

    @Override
    public boolean deleteComment(int commentId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
