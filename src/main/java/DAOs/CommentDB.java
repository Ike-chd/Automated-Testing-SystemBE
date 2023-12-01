package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Communications.CommentDAO;
import DAOs.DAOControllers.Users.FacultyMemberDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.Communication.Comment;
import Models.Users.FacultyMember;
import Models.Users.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommentDB extends DBConnection implements CommentDAO{
    private PreparedStatement ps;
    private ResultSet rs;
    private Connection con;
    private StudentDAO sdao = new StudentDB();
    private FacultyMemberDAO fadao = new FacultyMemberDB();
    @Override
    public Comment getComment(int commentId) {
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM Comments WHERE commentID = ?");
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
        try {
            con = getConnection();
            ps = con.prepareStatement("INSERT INTO Comments (comment, studentID, userID) VALUES (?, ?, ?)");
            ps.setString(1, comment.getComment());
            ps.setString(2, comment.getStudent().getUsername());
            ps.setInt(3, comment.getFaculty().getUserID());
            int affectedRows = ps.executeUpdate();
            if(affectedRows > 0){
                rs = ps.getGeneratedKeys();
                if(rs.next()){
                    comment.setCommentID(rs.getInt(1));
                    return true;
                }
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public boolean updateComment(Comment comment) {
        try {
            con = getConnection();
            ps = con.prepareStatement("UPDATE Comment SET comment = ? WHERE commentID = ?");
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getCommentID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public boolean deleteComment(Comment comment) {
        try {
            con = getConnection();
            ps = con.prepareStatement("DELETE FROM Comment WHERE commentID = ?");
            ps.setInt(1, comment.getCommentID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
        ex.printStackTrace();
        } finally {
            try {
                CloseStreams.closePreparedStatment(ps);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    @Override
    public List<Comment> getAllStudentComments(Student student) {
        List<Comment> studentComments = new ArrayList<>();
        try {
            con = getConnection();
            ps = con.prepareStatement("SELECT * FROM Comments WHERE studentID = ?");
            ps.setString(1, student.getUsername());
            rs = ps.executeQuery();
            while(rs.next()){
                Comment comment = extractCommentFromResultSet(rs);
                studentComments.add(comment);
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
        con = getConnection();
        try {
            ps = con.prepareStatement("SELECT * FROM Comments WHERE userID = ?");
            ps.setInt(1, faculty.getUserID());
            rs = ps.executeQuery();
            while(rs.next()){
                Comment comment = extractCommentFromResultSet(rs);
                facultyComments.add(comment);
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
    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException{
        int commentId = resultSet.getInt("commentID");
        int studentID = resultSet.getInt("studentID");
        int userID = resultSet.getInt("userID");
        String comment = resultSet.getString("comment");
        return new Comment(commentId, comment, sdao.getStudent(studentID), fadao.getFacultyMember(userID));
    }
}
