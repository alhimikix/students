package xyz.myfur.students.util;

import xyz.myfur.students.Data.Entities.Student;
import xyz.myfur.students.Data.repositories.StudentsRepository;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LoginUtil {
    public static Student isLogin(StudentsRepository sr, Cookie id, Cookie password){
        Student s = null;

        if (id==null||password==null){
            return null;
        }

        Optional<Student> su = sr.findById(Long.parseLong(id.getValue()));
        if (!su.isPresent()||password.getValue().equals("")){
            return null;
        }
        s = su.get();
        if (!s.getPassword().equals(password.getValue())){
            return null;
        }
        return s;
    }
    public static boolean Login(StudentsRepository sr, long id, String password,HttpServletResponse res){
        Cookie Id;
        Cookie Password;
        Optional<Student>su = sr.findById(id);
        if (!su.isPresent()||password.equals("")){
            return false;
        }
        Student s = su.get();
        if (!s.getPassword().equals(password)){
            return false;
        }
        Id = new Cookie("id",String.valueOf(id));
        Password = new Cookie("password",password);
        Id.setMaxAge(3600*24);
        Password.setMaxAge(3600*24);
        res.addCookie(Id);
        res.addCookie(Password);
        return true;
    }
    /**
     * Для проверки id
     * @param num  Строка
     * @return Если это число то id
     * если нет или оно отрицательное то -1
     * */
    public static long isLong(String num){
        try{
            long id = Long.parseLong(num);
            if (id<0){
                return -1;
            }
            return id;
        }catch (Exception e){
            return -1;
        }
    }
    public static void exit(Cookie id,Cookie password,HttpServletResponse response){
        if (id!=null){
            id.setMaxAge(0);
            response.addCookie(id);
        }
        if (password!=null){
            password.setMaxAge(0);
            response.addCookie(password);

        }
    }
}
