package xyz.myfur.students;

import freemarker.template.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.Yaml;
import xyz.myfur.students.Data.Group;
import xyz.myfur.students.Data.Part;
import xyz.myfur.students.util.Day;


import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@SpringBootApplication
public class StudentsApplication {
	
	@Autowired
	private Configuration fmk;

	@PostConstruct
	public void addConstructorDb(){
		Locale.setDefault(new Locale("uk_UA"));
		fmk.setTemplateUpdateDelayMilliseconds(1500);
		List<Part> partList = new ArrayList<>();
		try {
			File f =new File(System.getProperty("user.dir")+File.separator+"schedule.data");
			BufferedReader buff =new BufferedReader(new FileReader(f));
			ArrayList<String>lines = new ArrayList<>(buff.lines().filter((x)->{
				if (x.startsWith("#"))return false;
				return true;
			}).collect(Collectors.toList()));
			ArrayList<Part> arrlist = null;
			String name = null;
			for (int i = 0;i<lines.size();i++){
				if (lines.get(i).startsWith("<start>")){
					i++;
					name=lines.get(i).replaceAll(" ","");
					arrlist = new ArrayList<>();
					System.out.println("Start "+name);
					continue;
				}
				if (lines.get(i).startsWith("<end>")){
					System.out.println("End "+name);
					Group.getInstance().add(name,arrlist);
					continue;
				}
				String[]params = lines.get(i).split(",");
				Day d = Day.valueOf(params[0]);
				int hs = Integer.parseInt(params[1]);
				int ms = Integer.parseInt(params[2]);
				int ss = Integer.parseInt(params[3]);
				int he = Integer.parseInt(params[4]);
				int me = Integer.parseInt(params[5]);
				int se = Integer.parseInt(params[6]);
				arrlist.add(new Part(d,hs,ms,ss,he,me,se));
				System.out.println(d.getName()+" "+hs+" "+ms+" "+ss+" "+he+" "+me+" "+se);
			}
			
		}catch (Exception e){
			System.out.println("Розписания не добавлены!");
			System.out.println("Установка базового расписания");
			partList.add(new Part(Day.SATURDAY,8,10,15,16,10,15));
			partList.add(new Part(Day.SUNDAY,10,20,15,15,20,15));
			Group.getInstance().add("one",partList);
			System.out.println("Установка завешена");

		}
        partList.add(new Part(Day.SATURDAY,8,10,15,16,10,15));
        partList.add(new Part(Day.SUNDAY,10,20,15,15,20,15));
        Group.getInstance().add("one",partList);

	}
	public static void main(String[] args) {
		SpringApplication.run(StudentsApplication.class, args);
	}
}
