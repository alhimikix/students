package xyz.myfur.students;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import javax.annotation.PostConstruct;
import java.util.Locale;

@SpringBootApplication
public class StudentsApplication {
	
	@Autowired
	private Configuration fmk;

	@PostConstruct
	public void addConstructorDb(){
		Locale.setDefault(new Locale("uk_UA"));
		fmk.setTemplateUpdateDelayMilliseconds(1500);
		
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}
}
