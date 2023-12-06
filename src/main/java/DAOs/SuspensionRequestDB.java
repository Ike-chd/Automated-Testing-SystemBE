package DAOs;

import DAOs.CloseStreams.CloseStreams;
import DAOs.DAOControllers.Courses.CourseDAO;
import DAOs.DAOControllers.SuspensionRequest.SuspensionRequestDAO;
import DAOs.DAOControllers.Users.StudentDAO;
import DBConnection.DBConnection;
import Models.Communication.SuspensionRequest;
import Models.Users.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SuspensionRequestDB extends DBConnection implements SuspensionRequestDAO {
    private PreparedStatement ps;
    private ResultSet rs;
    private Statement statement;
    private CourseDAO cdao = new CourseDB();
    private StudentDAO sdao = new StudentDB();
    
    @Override
    public SuspensionRequest getSuspensionRequest(int ssId) {
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE SSID = ?");
            ps.setInt(1, ssId);
            rs = ps.executeQuery();
            if(rs.next()) {
                return extractSuspensionRequestFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally{
            try {
                CloseStreams.closeRsPs(rs, ps);
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean insertSuspensionRequest(SuspensionRequest ssRequest) {
        try {
            ps = getConnection().prepareStatement("INSERT INTO SuspensionRequests (studentID, requestID, duration, confirmID, reason, active, dateInitiated) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, ssRequest.getStudent().getUsername());
//            ps.setInt(2,ssRequest.getRequestId());
//            ps.setInt(3, ssRequest.getDuration());
//            ps.setInt(4,ssRequest.getConfirmId());
//            ps.setString(5,ssRequest.getReason());
//            ps.setBoolean(6,ssRequest.isActive());
//            ps.setTimestamp(7, Timestamp.valueOf(ssRequest.getDateInitiated()));
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
    public boolean updateSuspensionRequest(SuspensionRequest ssRequest) {
        try {
            ps = getConnection().prepareStatement("UPDATE SuspensionRequests SET studentID = ?, requestID = ?, duration = ?, confirmID = ?, reason = ?, active = ?, dateInitiated = ? WHERE SSID = ?");
            ps.setString(1,ssRequest.getStudent().getUsername());
//            ps.setInt(2,ssRequest.getRequestId());
//            ps.setInt(3,ssRequest.getDuration());
//            ps.setInt(4,ssRequest.getConfirmId());
//            ps.setString(5,ssRequest.getReason());
//            ps.setBoolean(6,ssRequest.isActive());
//            ps.setTimestamp(7,Timestamp.valueOf(ssRequest.getDateInitiated()));
//            ps.setInt(8,ssRequest.getSsId());
            int affectedRows = ps.executeUpdate();
            return affectedRows>0;
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
    public boolean deleteSuspensionRequest(SuspensionRequest ssRequest) {
        try {
            ps = getConnection().prepareStatement("DELETE FROM SuspensionRequests WHERE SSID = ?");
//            ps.setInt(1,ssRequest.getSsId());
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
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
    public List<SuspensionRequest> getAllSuspensionRequests() {
        List<SuspensionRequest> suspensionRequests = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            rs = statement.executeQuery("SELECT * FROM SuspensionRequests");
            while(rs.next()){
                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
                suspensionRequests.add(suspensionRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            
        }
        return suspensionRequests;
    }

    @Override
    public List<SuspensionRequest> getAllActiveSuspensionRequests() {
        List<SuspensionRequest> activeSuspensionRequests = new ArrayList<>();
        try {
            statement = getConnection().createStatement();
            rs = statement.executeQuery("SELECT * FROM SuspensionRequests WHERE active = true");
            while(rs.next()){
                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
                activeSuspensionRequests.add(suspensionRequest);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
                try {if(statement != null){
                statement.close();
            } else if(rs != null){
                    rs.close();
                }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            
        }
        return activeSuspensionRequests;
    }

    @Override
    public List<SuspensionRequest> getSuspensionRequestsByStudent(Student student) {
        List<SuspensionRequest> studentSuspensionRequest = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("SELECT * FROM SuspensionRequests WHERE studentID = ?");
            ps.setString(1,student.getUsername());
            rs = ps.executeQuery();
            while(rs.next()){
                SuspensionRequest suspensionRequest = extractSuspensionRequestFromResultSet(rs);
                studentSuspensionRequest.add(suspensionRequest);
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
        return studentSuspensionRequest;
    }
    private SuspensionRequest extractSuspensionRequestFromResultSet(ResultSet resultSet) throws SQLException {
        int ssId = resultSet.getInt("SSID");
        int studentId = resultSet.getInt("studentID");
        int requestId = resultSet.getInt("requestID");
        int duration = resultSet.getInt("duration");
        int confirmId = resultSet.getInt("confirmId");
        String reason = resultSet.getString("reason");
        boolean active = resultSet.getBoolean("active");
        Timestamp dateInitiated = resultSet.getTimestamp("dateInitiated");
     //   return new SuspensionRequest(ssId, sdao.getStudent(studentId), requestId, duration, confirmId, reason,active, dateInitiated.toLocalDateTime());
        return null;
    }
}
