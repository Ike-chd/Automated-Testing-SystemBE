package DAOs;

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

public class StudentAnswerDB implements StudentAnswerDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private Statement s;
    private StudentDAO sdao = new StudentDB();
    private QuestionDAO qdao = new QuestionDB();
    private TestDAO tdao = new TestDB();

    @Override
    public StudentAnswer getStudentAnswer(int qaId) {
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE qaID = ?");
            ps.setInt(1, qaId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractStudentAnswerFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertStudentAnswer(StudentAnswer studentAnswer) {
        try {
            ps = DBConnection.getConnection().prepareStatement("INSERT INTO Student_Answers (studentID, questionID, correctAns, testID) VALUES (?, ?, ?, ?)");
            ps.setInt(2, studentAnswer.getQuestion().getQuestionID());
            ps.setInt(3, studentAnswer.getCorrectAns());//TODO
            ps.setInt(4, studentAnswer.getTest().getTestID());
            int affectRows = ps.executeUpdate();
            return affectRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateStudentAnswer(StudentAnswer studentAnswer) {
        try {
            ps = DBConnection.getConnection().prepareStatement("UPDATE Student_Answers SET studentID = ?, questionID = ?, correctAns = ?, testID = ? WHERE qaID = ?");
            ps.setInt(2, studentAnswer.getQuestion().getQuestionID());
            ps.setInt(3, studentAnswer.getCorrectAns());
            ps.setInt(4, studentAnswer.getTest().getTestID());
            ps.setInt(5, studentAnswer.getQaId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteStudentAnswer(StudentAnswer studentAnswer) {
        try {
            ps = DBConnection.getConnection().prepareStatement("DELETE FROM Student_Answers WHERE qaID = ?");
            ps.setInt(1, studentAnswer.getQaId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<StudentAnswer> getStudentAnswers() {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            s = DBConnection.getConnection().createStatement();
            rs = s.executeQuery("SELECT * FROM Student_Answers");
            while (rs.next()) {
                StudentAnswer studentAnswer = extractStudentAnswerFromResultSet(rs);
                studentAnswers.add(studentAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentAnswers;
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByStudent(Student student) {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE studentID = ?");
            ps.setInt(1, student.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentAnswer studentAnswer = extractStudentAnswerFromResultSet(rs);
                studentAnswers.add(studentAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentAnswers;
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByQuestion(Question question) {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE questionID = ?");
            ps.setInt(1, question.getQuestionID());
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentAnswer studentAnswer = extractStudentAnswerFromResultSet(rs);
                studentAnswers.add(studentAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentAnswers;
    }

    @Override
    public List<StudentAnswer> getStudentAnswersByTest(Test test) {
        List<StudentAnswer> studentAnswers = new ArrayList<>();
        try {
            ps = DBConnection.getConnection().prepareStatement("SELECT * FROM Student_Answers WHERE testID = ?");
            ps.setInt(1, test.getTestID());
            rs = ps.executeQuery();
            while (rs.next()) {
                StudentAnswer studentAnswer = extractStudentAnswerFromResultSet(rs);
                studentAnswers.add(studentAnswer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return studentAnswers;
    }

    private StudentAnswer extractStudentAnswerFromResultSet(ResultSet resultSet) throws SQLException {
        int qaId = resultSet.getInt("qaID");
        int studentId = resultSet.getInt("studentID");
        int questionId = resultSet.getInt("questionID");
        int correctAns = resultSet.getInt("correctAns");
        int testId = resultSet.getInt("testID");
        return new StudentAnswer(qaId, sdao.getStudent(studentId), qdao.getQuestion(questionId), correctAns, tdao.getTest(testId));
    }
}
