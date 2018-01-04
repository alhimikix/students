package xyz.myfur.students;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.View;
import xyz.myfur.students.Data.Entities.Student;
import xyz.myfur.students.Data.repositories.StudentsRepository;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class StudentsApplication {

	@Autowired
	private Configuration fmk;
	@PostConstruct
	public void addConstructorDb(){
		fmk.setTemplateUpdateDelayMilliseconds(1500);
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}
}
