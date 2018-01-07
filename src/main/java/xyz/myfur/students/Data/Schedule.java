package xyz.myfur.students.Data;

import lombok.Getter;
import xyz.myfur.students.util.Day;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Schedule {
    private Schedule instance;
    private List<Part>partList;



    public Schedule(List<Part>partList){
        this.partList = partList;
        /*partList = new ArrayList<>();
        partList.add(new Part(Day.SATURDAY,8,10,15,16,10,15));
        partList.add(new Part(Day.SUNDAY,10,20,15,15,20,15));*/
    }


    /*public static Schedule getInstance() {
        Schedule localInstance = instance;
        if (localInstance == null) {
            synchronized (Schedule.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Schedule();
                }
            }
        }
        return localInstance;

    }*/
}
