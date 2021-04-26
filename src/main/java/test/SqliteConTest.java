package test;


import jdbc.SqliteConnt;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqliteConTest {

    @Test
    public void getCon() throws SQLException {
        Connection conn = SqliteConnt.getInstance();
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM account");
        int maxRows = statement.getMaxRows();
        System.out.println(maxRows);

        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("id"));
        }

        resultSet.close();

    }



}
