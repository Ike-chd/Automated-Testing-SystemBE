package DAOs.DAOControllers.Courses;

import Models.Courses.Topic;
import java.util.List;

public interface TopicDAO {

    public Topic getTopic(int topicId);

    public boolean insertTopic(Topic topic);

    public boolean deleteTopic(Topic topic);

    public boolean updateTopic(Topic topic);

    public List<Topic> allTopics();
}