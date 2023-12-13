package Services.ServicesInterfaces;

import Models.Communication.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> getAllComments();

    Comment postComment(Comment comment);

    Optional<Comment> getCommentById(int commentId);
}
