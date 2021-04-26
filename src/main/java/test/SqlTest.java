package test;

import Service.BookService;
import Service.TableService;
import Service.UserService;
import org.junit.Test;
import pojo.system.Booking;
import pojo.system.Table;
import pojo.user.Account;

import java.util.ArrayList;

public class SqlTest {


    @Test
    public void testTables(){
        TableService tableService = new TableService();
        ArrayList<Table> allTables = tableService.getAllTables();

        for(Table t : allTables){
            System.out.println(t.toString());
        }
    }


    @Test
    public void testLogin(){
        UserService userService = new UserService();
        Account login = userService.login("user1", "user1");
        System.out.println(login.toString());
    }


    @Test
    public void testbooks(){
        BookService bookService = new BookService();
        ArrayList<Booking> allBookings = bookService.getAllBookings();

        for(Booking t : allBookings){
            System.out.println(t.toString());
        }
    }

}
