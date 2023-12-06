package Services.ServicesInterfaces;

import Models.Communication.Comment;
import java.util.Optional;

public interface CommentService {
    public Optional<Comment> getComment();
    public boolean insertComment();
}
