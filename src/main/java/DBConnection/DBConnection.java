package DBConnection;

import java.sql.Connection;
import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnection {

    protected static Connection con;
    private static final BasicDataSource basicDatasource;

    static {
        basicDatasource = new BasicDataSource();
        basicDatasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDatasource.setUrl("jdbc:mysql://localhost:3306/ats?autoReconnect=true&useSSL=false");
        basicDatasource.setUsername( "root");
        basicDatasource.setPassword("root");
        basicDatasource.setMinIdle(10);
        basicDatasource.setMaxIdle(10);
        basicDatasource.setMaxOpenPreparedStatements(100);

        try {
            con = basicDatasource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return basicDatasource.getConnection();
    }
}
