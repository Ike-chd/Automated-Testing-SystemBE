package Services;

import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.TopicDB;
import Models.Courses.Topic;
import Services.ServicesInterfaces.TopicService;
import java.util.List;
import java.util.Optional;

public class TopicHandler implements TopicService {

    TopicDAO topdao = new TopicDB();

    @Override
    public Optional<Topic> getTopic(int topicId) {
        return Optional.ofNullable(topdao.getTopic(topicId));
    }

    @Override
    public boolean addTopic(Topic topic) {
        return topdao.insertTopic(topic);
    }

    @Override
    public boolean updateTopic(Topic topic) {
        return topdao.updateTopic(topic);
    }

    @Override
    public boolean deleteTopic(int topicId) {
        return topdao.deleteTopic(topicId);
    }

    @Override
    public List<Topic> getAllTopics() {
        return topdao.allTopics();
    }
    
    @Override
    public List<Topic> getAllTopicsInATest(int testId) {
        return topdao.getAllTopicsInATest(testId);
    }

}
