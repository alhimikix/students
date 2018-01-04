import xyz.myfur.students.util.CalendarMy;
import xyz.myfur.students.util.Month;

import java.util.*;

public class Test {
    public static void main(String[]a){
        Locale.setDefault(Locale.forLanguageTag("RU"));
        System.out.println(CalendarMy.getDay(System.currentTimeMillis()));
        System.out.println(Month.APRIL.name());
    }
}
