package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.QA.QuestionDAO;
import DAOs.DAOControllers.QA.StudentAnswerDAO;
import DAOs.DAOControllers.Tests.TestDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.QA.Question;
import Models.QA.StudentAnswer;
import Models.Tests.Test;
import Models.Users.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentAnswerDB extends DBConnection implements StudentAnswerDAO{

    private PreparedStatement ps;
    private ResultSet rs;
    private StudentDAO sdao = new StudentDB();
    private QuestionDAO qdao = new QuestionDB();
    private TestDAO tdao = new TestDB();

    @Override
    public StudentAnswer getStudentAnswer(int qaId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE qaID = ?");
            ps.setInt(1, qaId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractStudentAnswerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
    public boolean insertStudentAnswer(StudentAnswer studentAnswer) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Student_Answers (studentID, questionID, correctAns, testID) VALUES (?, ?, ?, ?)");
            ps.setInt(1, studentAnswer.getStudent().getUserID());
            ps.setInt(2, studentAnswer.getQuestion().getQuestionID());
            ps.setBoolean(3, studentAnswer.isCorrectAns());
            ps.setInt(4, studentAnswer.getTest().getTestID());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public boolean updateStudentAnswer(StudentAnswer studentAnswer) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Student_Answers SET studentID = ?, questionID = ?, correctAns = ?, testID = ? WHERE qaID = ?");
            ps.setInt(1, studentAnswer.getStudent().getUserID());
            ps.setInt(2, studentAnswer.getQuestion().getQuestionID());
            ps.setBoolean(3, studentAnswer.isCorrectAns());
            ps.setInt(4, studentAnswer.getTest().getTestID());
            ps.setInt(5, studentAnswer.getQaId());
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public boolean deleteStudentAnswer(int studentAnswer) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Student_Answers WHERE qaID = ?");
            ps.setInt(1, studentAnswer);
            affectedRows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return affectedRows == 1;
    }

    @Override
    public List<StudentAnswer> getStudentAnswers() {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Student_Answers");
            rs = ps.executeQuery();
            while (rs.next()) {
                studentAnswers.add(extractStudentAnswerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs,ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return studentAnswers;
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByStudent(Student student) {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE studentID = ?");
            ps.setInt(1, student.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                studentAnswers.add(extractStudentAnswerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return studentAnswers;
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByQuestion(Question question) {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE questionID = ?");
            ps.setInt(1, question.getQuestionID());
            rs = ps.executeQuery();
            while (rs.next()) {
                studentAnswers.add(extractStudentAnswerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return studentAnswers;
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByTest(Test test) {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE testID = ?");
            ps.setInt(1, test.getTestID());
            rs = ps.executeQuery();
            while (rs.next()) {
                studentAnswers.add(extractStudentAnswerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return studentAnswers;
    }

    private StudentAnswer extractStudentAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        int qaId = resultSet.getInt("qaID");
        int studentId = resultSet.getInt("studentID");
        int questionId = resultSet.getInt("questionID");
        boolean correctAns = resultSet.getBoolean("correctAns");
        int testId = resultSet.getInt("testID");
        return new StudentAnswer(qaId, sdao.getStudent(studentId), qdao.getQuestion(questionId), correctAns, tdao.getTest(testId));
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByQuestionAndStudent(Question question, Student student) {
        List<StudentAnswer> answers = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE studentID = ? AND questionID = ?");
            ps.setInt(1,student.getUserID());
            ps.setInt(2,question.getQuestionID());
            rs = ps.executeQuery();
            while (rs.next()) {
                answers.add(extractStudentAnswerFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answers;
    }
}
