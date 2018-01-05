package xyz.myfur.students.WebSite.Authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.myfur.students.Data.Entities.Journal;
import xyz.myfur.students.Data.Entities.Student;
import xyz.myfur.students.Data.Part;
import xyz.myfur.students.Data.Schedule;
import xyz.myfur.students.Data.repositories.JournalRepository;
import xyz.myfur.students.Data.repositories.StudentsRepository;
import xyz.myfur.students.util.CalendarMy;
import xyz.myfur.students.util.Day;
import xyz.myfur.students.util.LoginUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class UserControll {
    @Autowired
    private StudentsRepository sr;
    @Autowired
    private JournalRepository jr;
    @RequestMapping("/user")
    public String userinf(Map<String,Object>model,
                         // HttpServletRequest req,
                          HttpServletResponse res,
                          @CookieValue(value = "id",required = false) Cookie id,
                          @CookieValue(value = "password",required = false)Cookie password){


        Student student = LoginUtil.isLogin(sr,id,password,res);
        if (student!=null){
            model.put("first_name",student.getFirstname());
            model.put("last_name",student.getLastname());
            return "user_info";
        }else{
            return "redirect:/login";
        }


    }
    @RequestMapping("/add_record")
    public String addRecord(Map<String,Object>model,
                     //HttpServletRequest req,
                     HttpServletResponse res,
                     @CookieValue(value = "id",required = false) Cookie id,
                     @CookieValue(value = "password",required = false)Cookie password){
        Student s;
        if ((s = LoginUtil.isLogin(sr,id,password,res))==null){
            return "redirect:/login";
        }
        long time = System.currentTimeMillis();
        Day day = CalendarMy.getDay(time);
        int hours = CalendarMy.getHours(time);
        int minutes = CalendarMy.getMinutes(time);
        int seconds = CalendarMy.getSeconds(time);
        List<Part> arrayList =Schedule.getInstance().getPartList();
        boolean isLesson =false;
        Part p = null;
        for (Part part : arrayList) {
            if (part.getDay().equals(day)){
                if (hours>=part.getHour_start()&&hours<=part.getHour_end()){
                    if (minutes+60*hours>=part.getMin_start()+60*part.getHour_start()&&minutes+60*hours<=part.getMin_end()+60*part.getHour_end()){
                        if (seconds+3600*hours>=part.getSec_start()+3600*part.getHour_start()&&seconds+3600*hours<=part.getSec_end()+3600*part.getHour_end()){
                            isLesson=true;
                            p=part;
                            break;
                        }
                    }
                }
            }
        }
        if (!isLesson){
            model.put("message","Now you don't have lessons!");
            return "journal";
        }
        isLesson = false;
        Collection<Journal> list = jr.findJournalsByDayAndMonthAndYear((long)day.getId(),(long)CalendarMy.getMonth(time).getId(),CalendarMy.getYear(time));

        for (Journal j : list) {
             long time1 = j.getTime();
             Day day1 = CalendarMy.getDay(time1);
             int hours1 = CalendarMy.getHours(time1);
             int minutes1 = CalendarMy.getMinutes(time1);
             int seconds1 = CalendarMy.getSeconds(time1);
            if (p.getDay().equals(day1)){
                if (hours1>=p.getHour_start()&&hours1<=p.getHour_end()){
                    if (minutes1+60*hours1>=p.getMin_start()+60*p.getHour_start()&&minutes1+60*hours1<=p.getMin_end()+60*p.getHour_end()){
                        if (seconds1+3600*hours1>=p.getSec_start()+3600*p.getHour_start()&&seconds1+3600*hours1<=p.getSec_end()+3600*p.getHour_end()){
                            isLesson=true;
                            break;
                        }
                    }
                }
            }
        }
        if (isLesson){
            model.put("message","Вы уже отметелись на даной паре!");
            return "journal";
        }
        Journal j =new Journal();
        j.setTime(time);
        j.setDay((long)day.getId());
        j.setMonth((long)CalendarMy.getMonth(time).getId());
        j.setYear(CalendarMy.getYear(time));
        j.setStudentid(s.getId());
        jr.save(j);
        model.put("message","Добавлена запись - "+day.getName()+" "+hours+":"+minutes+":"+seconds);
        return "journal";
    }

}
