package xyz.myfur.students;

import freemarker.template.Configuration;
import org.hibernate.validator.internal.constraintvalidators.bv.NullValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.View;


import javax.annotation.PostConstruct;

@SpringBootApplication
public class StudentsApplication {
	
	@Autowired
	private Configuration fmk;
	@Autowired
	private NullValidator nullValidator;

	@PostConstruct
	public void addConstructorDb(){
		fmk.setTemplateUpdateDelayMilliseconds(1500);
		
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}
}
