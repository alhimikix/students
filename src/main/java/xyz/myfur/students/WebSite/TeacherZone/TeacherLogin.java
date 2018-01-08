package xyz.myfur.students.WebSite.TeacherZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.myfur.students.Data.Entities.Journal;
import xyz.myfur.students.Data.Entities.Student;
import xyz.myfur.students.Data.Entities.Teacher;
import xyz.myfur.students.Data.repositories.JournalRepository;
import xyz.myfur.students.Data.repositories.StudentsRepository;
import xyz.myfur.students.Data.repositories.TeachersRepository;
import xyz.myfur.students.WebSite.Authorization.Data;
import xyz.myfur.students.WebSite.CalendarController;
import xyz.myfur.students.util.CalendarMy;
import xyz.myfur.students.util.LoginUtil;
import xyz.myfur.students.util.Month;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherLogin {
    @Autowired
    private TeachersRepository tr;
    @Autowired
    private JournalRepository jk;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(@CookieValue(value = "tid",required = false) Cookie tid,
                        @CookieValue(value = "tpassword",required = false) Cookie tpassword,
                        HttpServletResponse res,Map<String,Object>model){
        Teacher teacher = isLogin(tr,tid,tpassword,res);
        if (teacher!=null){
           return  "redirect:/tdata";
        }
        model.put("message","");
        return "tlogin";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login_post(@RequestParam(name = "id",defaultValue = "")String Strid,
                             @RequestParam(name = "password",defaultValue = "") String password,
                             @CookieValue(value = "tid",required = false) Cookie tid,
                             @CookieValue(value = "tpassword",required = false) Cookie tpassword,
                             HttpServletResponse res, Map<String,Object>model){
        Teacher teacher = isLogin(tr,tid,tpassword,res);
        if (teacher!=null){
            return  "redirect:/teacher";
        }
        if (LoginUtil.isLong(Strid)==-1){
            model.put("message","Bad id");
            return "tlogin";
        }

        if (!Login(tr,LoginUtil.isLong(Strid),password,res)){
            model.put("message","Bad id or password");
            return "tlogin";
        }
        return "redirect:/teacher";
    }

    @RequestMapping(value = "/journal",method = RequestMethod.GET)
    public String journal_teacher(@CookieValue(value = "tid",required = false) Cookie tid,
                                  @CookieValue(value = "tpassword",required = false) Cookie tpassword,
                                  HttpServletResponse res, Map<String,Object>model){
        Teacher t = isLogin(tr,tid,tpassword,res);
        if (t==null)return "redirect:/teacher/login";
            List<Data> messages = new ArrayList<>();
            model.put("messages",messages);
            return "TJournal";

    }

    @RequestMapping(value = "/journal",method = RequestMethod.POST)
    public String journal_teacher_post(@RequestParam(name = "id",defaultValue = "")String Strid,
                                       @CookieValue(value = "tid",required = false) Cookie tid,
                                       @CookieValue(value = "tpassword",required = false) Cookie tpassword,
                                       HttpServletResponse res, Map<String,Object>model){

        Teacher t = isLogin(tr,tid,tpassword,res);
        if (t==null)return "redirect:/teacher/login";

        if (LoginUtil.isLong(Strid)==-1){
        List<Data> messages = new ArrayList<>();
        model.put("messages",messages);
        return "TJournal";
        }
        long time = System.currentTimeMillis();
        List<Data> messages = new ArrayList<>();
        List<Journal>jl = CalendarController.getJournals(LoginUtil.isLong(Strid), CalendarMy.getYear(time),CalendarMy.getMonth(time).getId(),jk);
        for (Journal jr : jl) {
            Data d =new Data(Month.month((int)jr.getMonth()).getName(),CalendarMy.getDate(jr.getTime())+"",
                    CalendarMy.getDay(jr.getTime()).getName(),
                    CalendarMy.getHours(jr.getTime())+":"+CalendarMy.getMinutes(jr.getTime())+":"+CalendarMy.getSeconds(jr.getTime()));
            messages.add(d);

        }

        messages.add(new Data("","","",""));
        model.put("messages",messages);
        return "TJournal";
    }

    public void teacher_data(){}
    @RequestMapping("")
    public String empty(@CookieValue(value = "tid",required = false) Cookie tid,
                        @CookieValue(value = "tpassword",required = false) Cookie tpassword,
                        HttpServletResponse res, Map<String,Object>model){
        Teacher t = isLogin(tr,tid,tpassword,res);
        if (t==null)return "redirect:/teacher/login";

        model.put("first_name",t.getFirstname());
        model.put("last_name",t.getLastname());
        return "TData";

    }




    public static Teacher isLogin(TeachersRepository tr, Cookie id, Cookie password, HttpServletResponse res){
        Teacher t = null;

        if (id==null||password==null){
            return null;
        }

        Optional<Teacher> su = tr.findById(Long.parseLong(id.getValue()));
        if (!su.isPresent()||password.getValue().equals("")){
            return null;
        }
        t = su.get();
        if (!t.getPassword().equals(password.getValue())){
            return null;
        }
        id.setMaxAge(3600*60);
        password.setMaxAge(3600*60);
        res.addCookie(id);
        res.addCookie(password);
        return t;
    }
    public static boolean Login(TeachersRepository sr, long id, String password,HttpServletResponse res){
        Cookie Id;
        Cookie Password;
        Optional<Teacher>su = sr.findById(id);
        if (!su.isPresent()||password.equals("")){
            return false;
        }
        Teacher s = su.get();
        if (!s.getPassword().equals(password)){
            return false;
        }
        Id = new Cookie("tid",String.valueOf(id));
        Password = new Cookie("tpassword",password);
        Id.setMaxAge(3600*24);
        Password.setMaxAge(3600*24);
        res.addCookie(Id);
        res.addCookie(Password);
        return true;
    }
}
