package xyz.myfur.students.WebSite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CalendarController {
    @RequestMapping("/journal")
    public String getWeekJournal(){
        return "journal";
    }
}
