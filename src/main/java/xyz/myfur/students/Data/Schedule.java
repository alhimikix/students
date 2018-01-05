package xyz.myfur.students.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.View;
import xyz.myfur.students.util.Day;

public class Schedule {

    public Schedule(){

    }

}
class Part{
    private Day day;
    private int hour_start;
    private int min_start;
    private int sec_start;
    private int hour_end;
    private int min_end;
    private int sec_end;


    private Part(){}

    public Part(Day day, int hour_start, int min_start, int sec_start, int hour_end, int min_end, int sec_end) {
        this.day = day;
        this.hour_start = hour_start;
        this.min_start = min_start;
        this.sec_start = sec_start;
        this.hour_end = hour_end;
        this.min_end = min_end;
        this.sec_end = sec_end;
    }

}