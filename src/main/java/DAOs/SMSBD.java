package DAOs;

import DAOs.DAOControllers.Communications.SMSDAO;
import DBConnection.DBConnection;
import Models.Communication.SMS;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SMSBD extends DBConnection implements SMSDAO {

    PreparedStatement ps;
    ResultSet rs;

    @Override
    public SMS getSMS(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<SMS> getAllSMS() {
        List<SMS> smses = new ArrayList<>();
        try {
            ps = getConnection().prepareStatement("Select * FROM sms");
            rs = ps.executeQuery();
            while (rs.next()) {
                SMS sms = new SMS();
                sms.setMessage(rs.getString(""));
                sms.setDate(getCalendar(rs.getString("").split("-")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return smses;
    }

    @Override
    public boolean addSMS(SMS sms) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean deleteSMS(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean updateSMS(SMS sms) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Calendar getCalendar(String[] date) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Integer.parseInt(date[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(date[1]));
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date[2]));
        cal.set(Calendar.HOUR, Integer.parseInt(date[3]));
        cal.set(Calendar.MINUTE, Integer.parseInt(date[4]));
        cal.set(Calendar.SECOND, Integer.parseInt(date[5]));
        cal.set(Calendar.MILLISECOND, Integer.parseInt(date[6]));
        return cal;
    }
}
