package xyz.myfur.students.WebSite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import xyz.myfur.students.Data.Entities.Student;
import xyz.myfur.students.Data.repositories.StudentsRepository;

import javax.servlet.http.Cookie;
import java.util.Map;
@Controller
public class MainSite {
    @Autowired
    private StudentsRepository sr;
    @RequestMapping("/")
    @ResponseBody
    public ModelAndView addConstructorDb(){
        return new ModelAndView("index");
    }
}
