package DAOs;

import DAOs.DAOControllers.Courses.TopicDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DBConnection.DBConnection;
import Models.Courses.Topic;
import Models.QA.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDB implements QuestionDAO{
    private PreparedStatement ps;
    private ResultSet rs;
    private DBConnection connection;
    private TopicDAO topDao = new TopicDB();
    @Override
    public Question getQuestion(int questionId) {
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM Questions WHERE questionID = ?");
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractQuestionFromResultSet(rs);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return null;
    }
    
    @Override
    public boolean insertQuestion(Question question) {
        try {
            ps = connection.getConnection().prepareStatement("INSERT INTO Questions (question, markAllocation, topicID) VALUES (?,?,?,?)");
            ps.setString(1, question.getQuestion());
            ps.setInt(2, question.getMarkAllocation());
            ps.setInt(3, question.getTopic().getTopicID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean deleteQuestion(int questionId) {
        try {
            ps = connection.getConnection().prepareStatement("DELETE FROM Questions WHERE questionID = ?");
            ps.setInt(1, questionId);
            int affectedRows = ps.executeUpdate();
            return affectedRows >0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean updateQuestion(Question question) {
        try {
            ps = connection.getConnection().prepareStatement("UPDATE Questions SET question = ?, markAllocation = ?, topicID = ? WHERE questionID = ?");
            ps.setString(1, question.getQuestion());
            ps.setInt(2, question.getMarkAllocation());
            ps.setInt(3, question.getTopic().getTopicID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    @Override
    public List<Question> allQuestionUnderATopic(Topic topic) {
        List <Question> questions = new ArrayList<>();
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM Questions WHERE topicID = ?");
            ps.setInt(1, topic.getTopicID());
            rs = ps.executeQuery();
            while(rs.next()){
                Question question = extractQuestionFromResultSet(rs);
                questions.add(question);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return questions;
    }
    
    private Question extractQuestionFromResultSet(ResultSet resultSet) throws SQLException {
        int questionID = resultSet.getInt("questionID");
        String question = resultSet.getString("question");
        int markAllocation = resultSet.getInt("markAllocation");
        int topicID = resultSet.getInt("topicID");
        return new Question(questionID,question,markAllocation,topDao.getTopic(topicID));
    }

    @Override
    public Question getQuestion() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
