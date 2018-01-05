package xyz.myfur.students.WebSite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.myfur.students.Data.Entities.Student;
import xyz.myfur.students.Data.repositories.StudentsRepository;
import xyz.myfur.students.util.LoginUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class CalendarController {
    @Autowired
    private StudentsRepository sr;
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
        return "journal";
    }
}
