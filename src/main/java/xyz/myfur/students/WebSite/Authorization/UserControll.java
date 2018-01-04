package xyz.myfur.students.WebSite.Authorization;

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
import java.util.Optional;

@Controller
public class UserControll {
    @Autowired
    private StudentsRepository sr;
    @RequestMapping("/user")
    public String userinf(Map<String,Object>model,
                          HttpServletRequest req,
                          HttpServletResponse res,
                          @CookieValue(value = "id",required = false) Cookie id,
                          @CookieValue(value = "password",required = false)Cookie password){


        Student student = LoginUtil.isLogin(sr,id,password);
        if (student!=null){
            model.put("first_name",student.getFirstname());
            model.put("last_name",student.getLastname());
            return "user_info";
        }else{
            return "redirect:/login";
        }


    }
}
