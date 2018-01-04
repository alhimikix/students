package xyz.myfur.students.WebSite.Authorization.Login;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import xyz.myfur.students.Data.repositories.StudentsRepository;
import xyz.myfur.students.util.LoginUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Map;



@Controller
public class LoginController {
    @Autowired
    private StudentsRepository sr;
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String get(Map<String,Object>model,
                      @CookieValue(value = "id",required = false) Cookie ids,
                      @CookieValue(value = "password",required = false)Cookie passwords){
        if (LoginUtil.isLogin(sr,ids,passwords)!=null){
            return "redirect:/user";
        }
        model.put("error","");
        return "login";
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String post(@RequestParam(name = "id",defaultValue = "")String Strid,
                       @RequestParam(name = "password",defaultValue = "") String password,
                       @CookieValue(value = "id",required = false) Cookie ids,
                       @CookieValue(value = "password",required = false)Cookie passwords,
                       HttpServletResponse res, HttpServletRequest req,
                       Map<String,Object>model) {

        long id = LoginUtil.isLong(Strid);
        if (id==-1){
            model.put("error","Bad Id");
            return "login";
        }
        if (!LoginUtil.Login(sr,id,password,res)){
            model.put("error","Bad Id or Password");
            return "login";
        }
        return "redirect:/user";
    }
    @RequestMapping("/exit")
    public String exit(Map<String,Object>model,
                       @CookieValue(value = "id",required = false) Cookie id,
                       @CookieValue(value = "password",required = false)Cookie passwords,
                       HttpServletResponse response){
        LoginUtil.exit(id,passwords,response);
        return "redirect:/login";
    }

}
