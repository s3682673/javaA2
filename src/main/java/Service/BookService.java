package Service;

import jdbc.SqliteConnt;
import pojo.system.Booking;
import pojo.system.Table;
import pojo.user.Account;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private static volatile Connection sqlConnection;

    public BookService(){
        sqlConnection = SqliteConnt.getInstance();
    }

    public Booking queryBookings(Integer year, Integer month, Integer day, Long tableId) {
        Booking book = null;
        try{
            String sql = "select * from book where tableId = \""+tableId+"\"and year = \""+year+"\" and month = \""+month +"\"and day = \""+day+"\"";
            Statement statement = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet != null ){
                while (resultSet.next()){
                    book = new Booking();
                    book.id = resultSet.getLong("id");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return book;

    }

    public boolean  insert(Booking booking) {
        Boolean res = false;
        synchronized(this){

            try{
                String sql = "insert into book (year,month ,day,userName,tableId) values ("+booking.year+","+booking.month+","+ booking.day+",\""+ booking.name+"\","+ booking.tableId+")";
                Statement statement = sqlConnection.createStatement();
                res = statement.execute(sql);

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return res;

        }
    }

    public ArrayList<Booking> getAllBookings(){
        ArrayList<Booking> books = new ArrayList<>();
        try{
            String sql = "select * from book";
            Statement statement = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet != null ){
                while (resultSet.next()){
                    Booking book = new Booking();
                    book.id = resultSet.getLong("id");

                    book.tableId = resultSet.getLong("tableId");
                    book.name = resultSet.getString("userName");
                    book.year = resultSet.getInt("year");
                    book.month = resultSet.getInt("month");
                    book.day = resultSet.getInt("day");

                    books.add(book);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }

    public ArrayList<Booking> getUserBookings(String name) {
        ArrayList<Booking> books = new ArrayList<>();
        try{
            String sql = "select * from book where userName = \""+name+"\"";
            Statement statement = sqlConnection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet != null ){
                while (resultSet.next()){
                    Booking book = new Booking();
                    book.id = resultSet.getLong("id");
                    book.tableId = resultSet.getLong("tableId");
                    book.name = resultSet.getString("userName");
                    book.year = resultSet.getInt("year");
                    book.month = resultSet.getInt("month");
                    book.day = resultSet.getInt("day");

                    books.add(book);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return books;
    }

    public void deleteById(Long id) {
        try{
            String sql = "delete from book where id = "+id;
            Statement statement = sqlConnection.createStatement();
            statement.execute(sql);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
