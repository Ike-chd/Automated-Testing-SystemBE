package Services.ServicesInterfaces;

import Models.Communication.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    
    public List<Comment> getAllComments();
    
    public List<Comment> getAllCommentsForMe(int stuID);
    
    public List<Comment> getAllCommentsIMade(int FacID);

    public boolean insertComment(Comment comment);

    public Optional<Comment> getCommentById(int commentId);
    
    public boolean deleteComment(int commentId);
}
