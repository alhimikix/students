package xyz.myfur.students.WebSite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.myfur.students.Data.Entities.Journal;
import xyz.myfur.students.Data.Entities.Student;
import xyz.myfur.students.Data.repositories.JournalRepository;
import xyz.myfur.students.Data.repositories.StudentsRepository;
import xyz.myfur.students.WebSite.Authorization.Data;
import xyz.myfur.students.util.CalendarMy;
import xyz.myfur.students.util.LoginUtil;
import xyz.myfur.students.util.Month;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class CalendarController {
    @Autowired
    private StudentsRepository sr;
    @Autowired
    private JournalRepository jk;
    @RequestMapping("/journal")
    public String getWeekJournal(Map<String,Object> model,
                                 HttpServletRequest req,
                                 HttpServletResponse res,
                                 @CookieValue(value = "id",required = false) Cookie id,
                                 @CookieValue(value = "password",required = false)Cookie password){
        Student s;
        if ((s = LoginUtil.isLogin(sr,id,password,res))==null){
            return "redirect:/login";
        }
        model.put("message","");
        long time = System.currentTimeMillis();
        List<Data> messages = new ArrayList<>();
        List<Journal>jl = CalendarController.getJournals(s.getId(), CalendarMy.getYear(time),CalendarMy.getMonth(time).getId(),jk);
        for (Journal jr : jl) {
            Data d =new Data(Month.month((int)jr.getMonth()).getName(),CalendarMy.getDate(jr.getTime())+"",
                    CalendarMy.getDay(jr.getTime()).getName(),
                    CalendarMy.getHours(jr.getTime())+":"+CalendarMy.getMinutes(jr.getTime())+":"+CalendarMy.getSeconds(jr.getTime()));
            messages.add(d);

        }

        messages.add(new Data("","","",""));
        model.put("messages",messages);
        return "journal";
    }
    public static List<Journal> getJournals(long id, long year , long mouth, JournalRepository jr){
        List<Journal>js;
        js = new ArrayList<>(jr.findJournalsByMonthAndYearAndStudentid(mouth, year, id));
        return js;
    }
}
