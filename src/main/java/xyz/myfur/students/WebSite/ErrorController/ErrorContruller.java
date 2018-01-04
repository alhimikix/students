package xyz.myfur.students.WebSite.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ErrorContruller {
    @ExceptionHandler(Exception.class)
    public void exeption(Exception exception){
        System.out.println("Error "+exception.getLocalizedMessage().toLowerCase());
    }

}

