// репозиторий для сортировки и фильтрации
package circus.kate.com.circusinfosystem.repository;

import java.util.List;
import circus.kate.com.circusinfosystem.model.Performer;
import org.springframework.data.jpa.repository.JpaRepository; // сюда входит findAll()
import org.springframework.data.jpa.repository.Query; //отвечает за sql запросы



public interface PerformerRepository extends JpaRepository<Performer, Long> { //наследник
    //тут поиск по введенному значению в переменную
    @Query("SELECT p FROM Performer p WHERE CONCAT(p.id, '', p.fullName, '', p.speciality, '', p.yearsExperience) LIKE %?1%")
    List<Performer> search(String keyword);
}
