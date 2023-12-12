package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Communications.CommentDAO;
import DAOs.DAOControllers.Users.FacultyMemberDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.Communication.Comment;
import Models.Users.FacultyMember;
import Models.Users.Student;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDB extends DBConnection implements CommentDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private StudentDAO sdao = new StudentDB();
    private FacultyMemberDAO fadao = new FacultyMemberDB();

    @Override
    public Comment getComment(int commentId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Comments WHERE commentID = ?");
            ps.setInt(1, commentId);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractCommentFromResultSet(rs);
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
    public boolean insertComment(Comment comment) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("INSERT INTO Comments (comment, studentID, userID) VALUES (?, ?, ?)");
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getStudent().getUserID());
            ps.setInt(3, comment.getFaculty().getUserID());
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
    public boolean updateComment(Comment comment) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("UPDATE Comment SET comment = ? WHERE commentID = ?");
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getCommentID());
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
    public boolean deleteComment(Comment comment) {
        int affectedRows = 0;
        try {
            ps = getConnection().prepareStatement("DELETE FROM Comment WHERE commentID = ?");
            ps.setInt(1, comment.getCommentID());
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
    public List<Comment> getAllStudentComments(Student student) {
        List<Comment> studentComments = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Comments WHERE studentID = ?");
            ps.setInt(1, student.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                studentComments.add(extractCommentFromResultSet(rs));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return studentComments;
    }

    @Override
    public List<Comment> getAllCommentsByFaculty(FacultyMember faculty) {
        List<Comment> facultyComments = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Comments WHERE userID = ?");
            ps.setInt(1, faculty.getUserID());
            rs = ps.executeQuery();
            while (rs.next()) {
                facultyComments.add(extractCommentFromResultSet(rs));
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
        return facultyComments;
    }

    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException {
        int commentId = resultSet.getInt("commentID");
        int studentID = resultSet.getInt("studentID");
        int userID = resultSet.getInt("userID");
        String comment = resultSet.getString("comment");
        return new Comment(commentId, comment, sdao.getStudent(studentID), fadao.getFacultyMember(userID));
    }
}