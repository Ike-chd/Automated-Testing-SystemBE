package DAOs.CloseStreams;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CloseStreams {
    public static void closePreparedStatment(PreparedStatement ps) throws SQLException{
        if(ps != null){
            ps.close();
        }
    }
    
    public static void closeRsPs(ResultSet rs, PreparedStatement ps) throws SQLException{
        closePreparedStatment(ps);
        if(rs != null){
            rs.close();
        }
    }
}