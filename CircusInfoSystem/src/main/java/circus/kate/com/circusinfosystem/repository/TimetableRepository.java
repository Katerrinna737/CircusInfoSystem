package circus.kate.com.circusinfosystem.repository;

import circus.kate.com.circusinfosystem.model.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimetableRepository extends JpaRepository<Timetable, Long> {

    List<Timetable> findAll(Sort sort); //сортировка

    @Query("SELECT t FROM Timetable t WHERE CONCAT(t.id, '', t.date, '', t.time, '', t.duration, '', t.cost) LIKE %?1%")
    List<Timetable> findByKeyword(@Param("keyword") String keyword); //поиск
}
