package xyz.myfur.students.util;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarMy {

    public static Day getDay(long time){
        Date d =new Date();
        GregorianCalendar gc =new GregorianCalendar();
        gc.setGregorianChange(d);
        System.out.println((gc.get(Calendar.DAY_OF_WEEK)));

        return Arrays.stream(Day.values()).filter((x)->{
            if (Day.valueOf(x.name()).getId()==gc.get(Calendar.DAY_OF_WEEK)){
                return true;
            }
            return false;
        }).findFirst().get();

    }
    public static int getHours(long time){
        Calendar c =new GregorianCalendar();
        c.setTime(new Date(time));
        return c.get(Calendar.HOUR_OF_DAY);
    }
    public static int getMinutes(long time){
        Calendar c =new GregorianCalendar();
        c.setTime(new Date(time));
        return c.get(Calendar.MINUTE);
    }
    public static int getSeconds(long time){
        Calendar c =new GregorianCalendar();
        c.setTime(new Date(time));
        return c.get(Calendar.SECOND);
    }
    public static int getDate(long time){
        Calendar c =new GregorianCalendar();
        c.setTime(new Date(time));
        return c.get(Calendar.DAY_OF_MONTH);
    }


}
