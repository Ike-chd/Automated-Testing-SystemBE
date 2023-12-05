package DAOs;

import DAOs.DAOControllers.Courses.TopicDAO;
import DBConnection.DBConnection;
import Models.Courses.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TopicDB extends DBConnection implements TopicDAO {

    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Topic getTopic(int topicId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Topics WHERE topicID = ?");
            ps.setInt(1, topicId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractTopicsFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertTopic(Topic topic) {
        int updated = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Topics (topicName, description, infoLink) VALUES(?,?,?)");
            ps.setString(1, topic.getTopicName());
            ps.setString(2, topic.getDescription());
            ps.setString(3, topic.getInfoLink());
            updated = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated == 1;
    }

    @Override
    public boolean deleteTopic(Topic topic) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM Topics WHERE topicID = ?");
            ps.setInt(1, topic.getTopicID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateTopic(Topic topic) {
        try {
            ps = getConnection().prepareStatement("UPDATE Topics SET topicName = ?, description = ?, infoLiStrnk = ? WHERE topicID = ?");
            ps.setString(1, topic.getTopicName());
            ps.setString(2, topic.getDescription());
            ps.setString(3, topic.getInfoLink());
            ps.setInt(4, topic.getTopicID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Topic> allTopics() {
        return null;
    }

    private Topic extractTopicsFromResultSet(ResultSet resultSet) throws SQLException {
        int topicID = resultSet.getInt("topicID");
        String topicName = resultSet.getString("topicName");
        String description = resultSet.getString("description");
        String infoLink = resultSet.getString("infoLink");
        return new Topic(topicID, topicName, description, infoLink);
    }
}
