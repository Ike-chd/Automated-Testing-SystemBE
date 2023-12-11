package DAOs;

import DAOs.CloseStreams.CloseStreams;
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

public class QuestionDB extends DBConnection implements QuestionDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private TopicDAO topDao = new TopicDB();

    @Override

    public Question getQuestion(int questionId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Questions WHERE questionID = ?");
            ps.setInt(1, questionId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractQuestionFromResultSet(rs);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean insertQuestion(Question question) {
        int updated = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Questions (question, markAllocation, topicID) "
                    + "VALUES (?,?,?)");
            ps.setString(1, question.getQuestion());
            ps.setInt(2, question.getMarkAllocation());
            ps.setInt(3, question.getTopic().getTopicID());
            updated = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated == 1;
    }

    @Override
    public boolean deleteQuestion(int questionId) {
        int updated = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Questions WHERE questionID = ?");
            ps.setInt(1, questionId);
            updated = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated == 1;
    }

    @Override
    public boolean updateQuestion(Question question) {
        int updated = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Questions SET question = ?, markAllocation = ?, topicID = ? WHERE questionID = ?");
            ps.setString(1, question.getQuestion());
            ps.setInt(2, question.getMarkAllocation());
            ps.setInt(3, question.getTopic().getTopicID());
            updated = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return updated == 1;
    }

    @Override
    public List<Question> allQuestionUnderATopic(Topic topic) {
        List<Question> questions = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Questions WHERE topicID = ?");
            ps.setInt(1, topic.getTopicID());
            rs = ps.executeQuery();
            while (rs.next()) {
                Question question = extractQuestionFromResultSet(rs);
                questions.add(question);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return questions;
    }
    
    @Override
    public List<Question> allQuestionsUnderATest(int testId) {
        List<Question> questions = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Test_Questions WHERE testID = ?");
            ps.setInt(1, testId);
            rs = ps.executeQuery();
            while (rs.next()) {
                int questionId = rs.getInt("questionID");
                Question question = getQuestion(questionId);
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
        return new Question(questionID, question, markAllocation, topDao.getTopic(topicID));
    }

}
