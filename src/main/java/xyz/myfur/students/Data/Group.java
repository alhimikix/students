package xyz.myfur.students.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Group {

    private static volatile Group instance;
    private Map<String,Schedule> schedules = new HashMap<>();

    public void add(String groupName,List<Part>schedule){
        schedules.put(groupName,new Schedule(schedule));
    }

    public Schedule getSchedules(String name) {
        return schedules.get(name);
    }

    public static Group getInstance() {
        Group localInstance = instance;
        if (localInstance == null) {
            synchronized (Group.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new Group();
                }
            }
        }
        return localInstance;

    }
}
