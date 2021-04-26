package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnt {

    private static volatile Connection sqlConnection;
    private static final String sql = "jdbc:sqlite://Users/dracula/Desktop/hotdesking.db";



    static {
        try {
            sqlConnection = DriverManager.getConnection(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static Connection getInstance(){
        return sqlConnection;
    }

}
