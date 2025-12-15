package circus.kate.com.circusinfosystem.service;

import java.util.List;
import java.util.stream.Collectors;

import circus.kate.com.circusinfosystem.model.Performer;
import circus.kate.com.circusinfosystem.repository.PerformerRepository;
import org.springframework.beans.factory.annotation.Autowired; //для связи зависимостей
import org.springframework.stereotype.Service;

@Service
public class PerformerService {

    @Autowired
    private PerformerRepository repo;

    public List<Performer> listAll(String keyword) { //коллекция и метод, отвечающие за поиск в системе
        if (keyword != null) {
            return repo.search(keyword); // если нашли в таблице веденного артиста, то возвращаем инфо по нему
        }
        return repo.findAll(); //если не нашли введенного артиста, то никакие значения не будут выведены
    }

    public List<Performer> findAllSorted(String sortField, String sortDir) { //метод для сортировки
        String sortFieldToUse =  sortField;
        if (!List.of("id", "fullName", "speciality", "yearsExperience").contains(sortFieldToUse)) {
            sortFieldToUse = "id";
        } else {
            sortFieldToUse = sortField;
        }

        List<Performer> allPerformers = repo.findAll(); //получаем все записи и сортируем в java
        boolean isDesc = sortDir.equalsIgnoreCase("desc"); //определяем направление сортировки

        String finalSortFieldToUse = sortFieldToUse;
        return allPerformers.stream()
                .sorted((p1, p2) -> {
                    int result;
                    switch (finalSortFieldToUse) {
                        case "id":
                            result = Long.compare(p1.getId(), p2.getId());
                            break;
                        case "fullName":
                            result = p1.getFullName().compareToIgnoreCase(p2.getFullName());
                            break;
                        case "speciality":
                            result = p1.getSpeciality().compareToIgnoreCase(p2.getSpeciality());
                            break;
                        case "yearsExperience":
                            result = Float.compare(p1.getYearsExperience(), p2.getYearsExperience());
                            break;
                        default:
                            result = 0;
                    }
                    return isDesc ? -result : result;
                })
                .collect(Collectors.toList());
    }

    public void save(Performer performer) { //метод сохранения
        repo.save(performer);
    } //сохранение

    public Performer get(Long id) { // метод редактирования
        return repo.findById(id).get();
    } //редактирование

    public void delete(Long id) { //метод удаления
        repo.deleteById(id);
    } //удаление
}
