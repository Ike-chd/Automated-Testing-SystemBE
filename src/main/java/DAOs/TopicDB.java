package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.TopicDAO;
import DBConnection.DBConnection;
import Models.Courses.Topic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TopicDB extends DBConnection implements TopicDAO{
    private PreparedStatement ps;
    private ResultSet rs;

    @Override
    public Topic getTopic(int topicId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Topics WHERE topicID = ?");
            ps.setInt(1, topicId);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractTopicsFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
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
            ps.setString(3,topic.getInfoLink());
            updated = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
             ex.printStackTrace();
            }
        }
        return updated == 1;
    }
    
    @Override
    public boolean deleteTopic(int topic) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM Topics WHERE topicID = ?");
            ps.setInt(1, topic);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            
        }
        return false;
    }
    @Override
    public boolean updateTopic(Topic topic) {
        try {
            ps = getConnection().prepareStatement("UPDATE Topics SET topicName = ?, description = ?, infoLink = ? WHERE topicID = ?");
            ps.setString(1, topic.getTopicName());
            ps.setString(2, topic.getDescription());
            ps.setString(3,topic.getInfoLink());
            ps.setInt(4,topic.getTopicID());
            int affectedRows = ps.executeUpdate();
            return affectedRows >0;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public List<Topic> allTopics() {
        List<Topic> topics = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Topics");
            rs = ps.executeQuery();
            while(rs.next()){
                topics.add(extractTopicsFromResultSet(rs));
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
        }finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return topics;
    }
    private Topic extractTopicsFromResultSet(ResultSet resultSet) throws SQLException{
        int topicID = resultSet.getInt("topicID");
        String topicName = resultSet.getString("topicName");
        String description = resultSet.getString("description");
        String infoLink = resultSet.getString("infoLink");
        return new Topic(topicID,topicName,description,infoLink);
    }
    
    @Override
    public void insertTopicIntoTest(int testId, int topicId){
        try {
            ps = getConnection().prepareStatement("INSERT INTO test_topics (tt_testID, tt_topicID) VALUES (?,?)");
            ps.setInt(1, testId);
            ps.setInt(2, topicId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    @Override
    public List<Topic> getAllTopicsInATest(int id){
        List<Topic> questions = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM test_topics WHERE tt_testID = ?");
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                int tID = rs.getInt("tt_topicID");
                try (PreparedStatement ps2 = getConnection().prepareStatement("SELECT * FROM Topics WHERE topicID = ?")) {
                    ps2.setInt(1, tID);
                    try (ResultSet rs2 = ps2.executeQuery()) {
                        while (rs2.next()) {
                            questions.add(extractTopicsFromResultSet(rs2));
                        }
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return questions;
    }
}