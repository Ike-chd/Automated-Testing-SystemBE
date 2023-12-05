package Services;

import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.TopicDB;
import Models.Courses.Topic;
import Services.ServicesInterfaces.TopicService;
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
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteTopic(int topicId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
