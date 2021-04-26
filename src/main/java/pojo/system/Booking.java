package pojo.system;

import java.util.Date;

public class Booking {

    public Long id;

    public Integer year;

    public Integer month;

    public Integer day;

    public Long tableId;
    public String name;

    public Booking(Integer year, Integer month, Integer day, Long id, String name) {
        this.year=year;
        this.month=month;
        this.day=day;
        this.tableId=id;
        this.name = name;
    }

    public Booking() {

    }
}
