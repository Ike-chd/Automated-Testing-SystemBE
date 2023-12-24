package Services;

import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.TopicDB;
import Models.Courses.Topic;
import Models.Tests.Test;
import Services.ServicesInterfaces.TestService;
import Services.ServicesInterfaces.TopicService;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class TopicHandler implements TopicService {

    TopicDAO topdao = new TopicDB();
    TestService ts = new TestHandler();

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
    
    @Override
    public Set<Integer> getAllTopicsInACourse(int courseID){
        Set<Integer> topicIDs = new HashSet<>();
        List<Test> tests = ts.allTestsInACourse(courseID);
        for (Test test : tests) {
            List<Topic> topics = topdao.getAllTopicsInATest(test.getTestID());
            for (Topic topic : topics) {
                topicIDs.add(topic.getTopicID());
            }
        }
        return topicIDs;
    }
}
