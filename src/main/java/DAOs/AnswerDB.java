package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.QA.AnswerDAO;
import DAOs.DAOControllers.QA.QuestionDAO;
import DBConnection.DBConnection;
import Models.QA.Answer;
import Models.QA.Question;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AnswerDB extends DBConnection implements AnswerDAO{
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement s;
    private QuestionDAO qdao = new QuestionDB();

    @Override
    public Answer getAnswer(int answerId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Answers WHERE answerID = ?");
            ps.setInt(1, answerId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractAnswerFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean insertAnswer(Answer answer) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Answers (questionID, answer, correctAnswer) VALUES(?,?,?)");
            ps.setInt(1, answer.getQuestion().getQuestionID());
            ps.setString(2, answer.getAnswer());
            ps.setBoolean(3, answer.isCorrect());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
           ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public boolean updateAnswer(Answer answer) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Answers SET questionID = ?, answer = ?, correctAnswer = ? WHERE answerID = ?");
            ps.setInt(1, answer.getQuestion().getQuestionID());
            ps.setString(2, answer.getAnswer());
            ps.setBoolean(3, answer.isCorrect());
            ps.setInt(4, answer.getAnswerID());
            affectedRows = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public boolean deleteAnswer(int id) {
        int rowsAffected = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Answer WHERE answerID = ?");
            ps.setInt(1, id);
            rowsAffected = ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowsAffected == 1;
    }

    @Override
    public List<Answer> allQuestionAnswers(Question question) {
        List<Answer> answers = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Answers WHERE questionID = ?");
            ps.setInt(1, question.getQuestionID());
            rs = ps.executeQuery();
            while (rs.next()) {
                Answer answer = extractAnswerFromResultSet(rs);
                answers.add(answer);
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
        return answers;
    }

    private Answer extractAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        int answerId = resultSet.getInt("answerID");
        String answer = resultSet.getString("answer");
        int questionID = resultSet.getInt("questionID");
        boolean correctAnswer = resultSet.getBoolean("correctAnswer");
        return new Answer(answerId, answer, correctAnswer, qdao.getQuestion(questionID));
    }

    @Override
    public boolean[] insertAnswers(List<Answer> answers) {
        boolean[] insertedAnswers = new boolean[answers.size()];
        int i = 0;
        for (Answer answer : answers) {
            insertedAnswers[i] =  insertAnswer(answer);
            i++;
        }
        return insertedAnswers;
    }
}
