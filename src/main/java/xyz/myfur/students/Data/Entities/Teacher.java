package xyz.myfur.students.Data.Entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Setter
@Getter
@ToString
@Entity
@Table(name = "teachers")
public class Teacher {
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
    private String rank;
    @Column
    private String Arhived;
}
