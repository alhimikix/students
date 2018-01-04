package xyz.myfur.students.Data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.myfur.students.Data.Entities.Student;

import java.util.Collection;

@Repository
public interface StudentsRepository extends CrudRepository<Student,Long>{

    /*
    * Запросы к базе данных
    * Получаем списки студентов
    * 1.По имени
    * 2.По Фамилии
    * 3.1 + 2
    * 4.1 + параметр Архивации студента
    * 5.2 + параметр Архивации студента
    * 6.3 + параметр Архивации студента
    * */
    /*1*/Collection<Student>findStudentsByFirstname(String name);

    /*2*/Collection<Student>findStudentsByLastname(String lastname);

    /*3*/Collection<Student>findStudentsByFirstnameAndLastname(String firstname,String LastName);

    ///*4*/Collection<Student>findStudentsByFirstnameAndArhived(String firstname,Boolean arhived);

    /*5*///Collection<Student>findStudentsByLastnameAndArhivedIsTrue(String lastname);

    /*6*///Collection<Student>findStudentsByFirstnameAndLastnameAndArhivedIsTrue(String firstname,String LastName);


}
