package xyz.myfur.students.Data;

import xyz.myfur.students.util.Day;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private static volatile Schedule instance;
    private List<Part>partList;

    public List<Part> getPartList() {
        return partList;
    }

    private Schedule(){
        partList = new ArrayList<>();
        partList.add(new Part(Day.SATURDAY,8,10,15,10,10,15));
        partList.add(new Part(Day.SATURDAY,10,20,15,11,20,15));
    }


    public static Schedule getInstance() {
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

    }
}
