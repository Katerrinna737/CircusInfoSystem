package circus.kate.com.circusinfosystem.service;

import circus.kate.com.circusinfosystem.model.Timetable;
import circus.kate.com.circusinfosystem.repository.TimetableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimetableService {

    @Autowired
    private TimetableRepository repository;

    public List<Timetable> listAll() {
        return repository.findAll();
    }

    //метод для сортировки
    public List<Timetable> listAllSorted(Sort sort) {
        return repository.findAll(sort);
    }
    //метод для поиска по ключу
    public List<Timetable> findByKeyword(String keyword) {
        return repository.findByKeyword(keyword);
    }

    public void save(Timetable timetable) {
        repository.save(timetable);
    }

    public Timetable get(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Мероприятие не найдено"));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
