package Service;

import jdbc.SqliteConnt;
import pojo.system.Table;
import pojo.user.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TableService {

    private static volatile Connection sqlConnection;

    public TableService(){
        sqlConnection = SqliteConnt.getInstance();
    }


    public ArrayList<Table> getAllTables(){
        ArrayList<Table> tables = new ArrayList<>();
        try{
            String sql = "select * from tables";
            Statement statement = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet != null ){
                while (resultSet.next()){
                    Table table = new Table();
                    table.id = resultSet.getLong("id");
                    table.status = resultSet.getInt("status");

                    tables.add(table);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tables;
    }



    public void blockTable(Long id ) {
        try{
            String sql = "update tables SET status = 1 where id = "+ id ;
            Statement statement = sqlConnection.createStatement();
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
