package xyz.myfur.students.Data.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import xyz.myfur.students.Data.Entities.Journal;

import java.util.Collection;

@Repository
public interface JournalRepository extends CrudRepository<Journal,Long> {
    Collection<Journal>findJournalsByDayAndMonthAndYear(long day,long month,long year);
    Collection<Journal>findJournalsByMonthAndYear(long month,long year);
    Collection<Journal>findJournalsByYear(long year);
}
