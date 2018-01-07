package xyz.myfur.students.Data.Entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.service.spi.InjectService;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "STUDENTS")
public class Student {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String firstname;
    @Column
    private String lastname;
    @Column
    private String password;
    @Column
    private String Arhived;
    @Column
    private String grou;
}
