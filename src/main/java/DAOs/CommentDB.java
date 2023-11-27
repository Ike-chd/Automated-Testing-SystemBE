package DAOs;

import DAOs.DAOControllers.Communications.CommentDAO;
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


public class CommentDB implements CommentDAO{
    private PreparedStatement ps;
    private ResultSet rs;
    private DBConnection connection;

    @Override
    public Comment getComment(int commentId) {
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM Comments WHERE commentID = ?");
            ps.setInt(1, commentId);
            rs = ps.executeQuery();
            if(rs.next()){
                return extractCommentFromResultSet(rs);
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insertComment(Comment comment) {
        try {
            ps = connection.getConnection().prepareStatement("INSERT INTO Comments (comment, studentID, userID) VALUES (?, ?, ?)");
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getStudent().getStudentNum());
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
        }
        return false;
    }

    @Override
    public boolean updateComment(Comment comment) {
        try {
            ps = connection.getConnection().prepareStatement("UPDATE Comment SET comment = ? WHERE commentID = ?");
            ps.setString(1, comment.getComment());
            ps.setInt(2, comment.getCommentID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteComment(Comment comment) {
        try {
            ps = connection.getConnection().prepareStatement("DELETE FROM Comment WHERE commentID = ?");
            ps.setInt(1, comment.getCommentID());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Comment> getAllStudentComments(Student student) {
        List<Comment> studentComments = new ArrayList<>();
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM Comments WHERE studentID = ?");
            ps.setInt(1, student.getStudentNum());
            rs = ps.executeQuery();
            while(rs.next()){
                Comment comment = extractCommentFromResultSet(rs);
                studentComments.add(comment);
            }
        } catch (SQLException ex) {
           ex.printStackTrace();
        }
        return studentComments;
    }

    @Override
    public List<Comment> getAllCommentsByFaculty(FacultyMember faculty) {
        List<Comment> facultyComments = new ArrayList<>();
        try {
            ps = connection.getConnection().prepareStatement("SELECT * FROM Comments WHERE userID = ?");
            ps.setInt(1, faculty.getUserID());
            rs = ps.executeQuery();
            while(rs.next()){
                Comment comment = extractCommentFromResultSet(rs);
                facultyComments.add(comment);
            }
        } catch (SQLException ex) {
        ex.printStackTrace();
        }
        return facultyComments;
    }
    
    private Comment extractCommentFromResultSet(ResultSet resultSet) throws SQLException{
        int commentId = resultSet.getInt("commentID");
        int studentID = resultSet.getInt("studentID");
        int userID = resultSet.getInt("userID");
        String comment = resultSet.getString("comment");
        
        Student student = getStudentById(studentID);
        FacultyMember facultyMember = getFacultyMemberById(userID);
        
        return new Comment(commentId, comment, student, facultyMember);
    }
    
    private Student getStudentById(int studentId) throws SQLException{
        ps = connection.getConnection().prepareStatement("SELECT * FROM Students WHERE studentID = ?");
        ps.setInt(1, studentId);
        rs = ps.executeQuery();
        if(rs.next()){
            return new Student();//TODO please resolve the Student class
        }
        return null;
    }
    
    private FacultyMember getFacultyMemberById(int facultyId) throws SQLException{
        ps = connection.getConnection().prepareStatement("SELECT * FROM Users WHERE userID = ?");
        ps.setInt(1, facultyId);
        rs = ps.executeQuery();
        if(rs.next()){
            return new FacultyMember();//TODO please resolve the Faculty Member class
        }
        return null;
    }
}
