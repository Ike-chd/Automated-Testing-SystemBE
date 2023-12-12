package Services.ServicesInterfaces;

import Models.Communication.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    void postComment(Comment comment);

    Optional<Comment> getCommentById(int commentId);

    List<Comment> getAllComments();
}
