package xyz.myfur.students;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xyz.myfur.students.Data.Group;
import xyz.myfur.students.Data.Part;
import xyz.myfur.students.util.Day;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class StudentsApplication {
	
	@Autowired
	private Configuration fmk;

	@PostConstruct
	public void addConstructorDb(){
		Locale.setDefault(new Locale("uk_UA"));
		fmk.setTemplateUpdateDelayMilliseconds(1500);
		List<Part> partList = new ArrayList<>();
        partList.add(new Part(Day.SATURDAY,8,10,15,16,10,15));
        partList.add(new Part(Day.SUNDAY,10,20,15,15,20,15));
		Group.getInstance().add("one",partList);
	}
	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}
}
