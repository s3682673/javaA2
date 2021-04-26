package Service;

import jdbc.SqliteConnt;
import pojo.system.Table;
import pojo.user.Account;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserService {

    private static volatile Connection sqlConnection;

    public UserService(){
        sqlConnection = SqliteConnt.getInstance();
    }

    public boolean register(String user, String password){
        boolean execute = false;
        try{
            String sql = "insert into account (name,password) values (\""+user+"\",\""+password+"\")";
            Statement statement = sqlConnection.createStatement();
            execute = statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return execute;
    }

    public Account login(String name, String password) {
        Account loginUser = null;
        try{
            String sql = "select * from account where name = \""+name+"\"and " + "password = \"" +password+"\"";
            Statement statement = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet != null ){
                while (resultSet.next()){
                    loginUser = new Account();
                    loginUser.id = resultSet.getLong("id");
                    loginUser.name = resultSet.getString("name");
                    loginUser.password = resultSet.getString("password");
                    loginUser.type = resultSet.getInt("type");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return loginUser;

    }

    public boolean changePassword(String name, char[] password) {

        boolean execute = false;

        String sql = "update account set password = \""+ String.valueOf(password)+ "\" where name = \""+name+"\"";
        Statement statement = null;
        try {
            statement = sqlConnection.createStatement();
            execute = statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return execute;

    }

    public ArrayList<Account> getAllUsers() {

        ArrayList<Account> execute = new ArrayList<>();

        String sql = "select * from account";
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = sqlConnection.createStatement();
            resultSet = statement.executeQuery(sql);
            if(resultSet != null ){
                while (resultSet.next()){
                    Account account = new Account();
                    account.id = resultSet.getLong("id");
                    account.name = resultSet.getString("name");
                    account.type = resultSet.getInt("type");
                    execute.add(account);
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return execute;
    }

    public void updateAdmin(Long id) {

        String sql = "update account set type = 0 where id = "+id;
        Statement statement = null;
        try {
            statement = sqlConnection.createStatement();
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
