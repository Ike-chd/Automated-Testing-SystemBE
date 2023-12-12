package DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnection {

    protected static Connection con;
    private static final BasicDataSource basicDatasource;

    static {
        basicDatasource = new BasicDataSource();
        basicDatasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDatasource.setUrl("jdbc:mysql://localhost:3306/ats?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false");
        basicDatasource.setUsername("root");
        basicDatasource.setPassword("root");
        basicDatasource.setMinIdle(10);
        basicDatasource.setMaxIdle(10);
        basicDatasource.setMaxOpenPreparedStatements(100);
        try {
            con = basicDatasource.getConnection();
        } catch (SQLException e) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, "DBConnection failure", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return basicDatasource.getConnection();
    }
}
