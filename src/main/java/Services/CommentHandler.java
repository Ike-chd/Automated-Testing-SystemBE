package Services;

import Models.Communication.Comment;
import Services.ServicesInterfaces.CommentService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class CommentHandler implements CommentService {

    private static final Map<Integer, Comment> commentDatabase = new HashMap<>();
    private static int commentIdCounter = 1;

    @Override
    public void postComment(Comment comment) {
        int commentId = commentIdCounter++;
        comment.setCommentID(commentId);
        commentDatabase.put(commentId, comment);
    }

    @Override
    public Optional<Comment> getCommentById(int commentId) {
        return Optional.ofNullable(commentDatabase.get(commentId));
    }

    @Override
    public List<Comment> getAllComments() {
        return new ArrayList<>(commentDatabase.values());
    }
}
