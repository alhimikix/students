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
        Part p = new Part(Day.FRIDAY,19,0,0,22,0,0);
        partList.add(p);
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
