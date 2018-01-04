package xyz.myfur.students.Data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.myfur.students.Data.Entities.Teacher;
@Repository
public interface TeachersRepository extends CrudRepository<Teacher,Long> {
}
