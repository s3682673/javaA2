package pojo.user;

public class Account {

    public Long id;
    public String name;
    public String password;

    /*
    * 0 - admin
    * 1 - employee
    */
    public Integer type;


    @Override
    public String toString() {
        return "name: "+ name+", password: " + password + ", id: "+ id;
    }
}
