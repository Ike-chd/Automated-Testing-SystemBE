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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class CommentDB extends DBConnection implements CommentDAO {

    private PreparedStatement ps;
    private ResultSet rs;
    private StudentDAO studentDAO = new StudentDB();
    private FacultyMemberDAO facultyMemberDAO = new FacultyMemberDB();

    @Override
    public Comment getComment(int commentId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM Comments WHERE commentID = ?");
            ps.setInt(1, commentId);
            rs = ps.executeQuery();
            if (rs.next()) {
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
            ps = getConnection().prepareStatement("INSERT INTO Comments (studentID, userID, comment, commentDate) VALUES (?, ?, ?, ?)");

            ps.setInt(1, comment.getUserID());
            ps.setInt(2, comment.getUserID());
            ps.setString(3, comment.getComment());
            ps.setTimestamp(4, (Timestamp) comment.getCommentDate());
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
            ps = getConnection().prepareStatement("UPDATE Comments SET comment = ?, commentDate = ? WHERE commentID = ?");
            ps.setString(1, comment.getComment());
            ps.setTimestamp(2, (Timestamp) comment.getCommentDate());
            ps.setInt(3, comment.getCommentID());
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
            ps = getConnection().prepareStatement("DELETE FROM Comments WHERE commentID = ?");
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
        } finally {
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
        String commentText = resultSet.getString("comment");
        java.sql.Timestamp commentDate = resultSet.getTimestamp("commentDate");

        Student student = studentDAO.getStudent(studentID);
        FacultyMember facultyMember = facultyMemberDAO.getFacultyMember(userID);

        return new Comment(commentId, commentText, student, facultyMember, commentDate);
    }
}
