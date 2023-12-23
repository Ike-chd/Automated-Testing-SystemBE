package Services.ServicesInterfaces;

import Models.Courses.Topic;
import java.util.List;
import java.util.Optional;

public interface TopicService {

    public Optional<Topic> getTopic(int topicId);

    public boolean addTopic(Topic topic);

    public boolean updateTopic(Topic topic);

    public boolean deleteTopic(int topicId);

    public List<Topic> getAllTopics();
    
    public List<Topic> getAllTopicsInATest(int testId);
}