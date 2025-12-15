package circus.kate.com.circusinfosystem.controller;

import circus.kate.com.circusinfosystem.model.Timetable;
import circus.kate.com.circusinfosystem.service.TimetableService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/timetable")
public class TimetableController {

    @Autowired
    private TimetableService service;

    // показать список мероприятий с сортировкой
    @GetMapping
    public String viewTimetable(
            Model model,
            @RequestParam(defaultValue = "") String keyword, // для поиска
            @RequestParam(defaultValue = "id") String sortField, // поле сортировки
            @RequestParam(defaultValue = "asc") String sortDir // направление сортировки
    ) {
        List<Timetable> timetables;
        if (keyword != null && !keyword.trim().isEmpty()) {
            // поиск по ключевому слову
            timetables = service.findByKeyword(keyword);
        } else {
            // сортировка
            Sort.Direction direction = sortDir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
            Sort sort = Sort.by(direction, sortField);
            timetables = service.listAllSorted(sort);
        }

        model.addAttribute("timetables", timetables);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("keyword", keyword); // передаем keyword, если нужен поиск

        return "timetable";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("timetable", new Timetable());
        return "add_timetable";
    }

    @PostMapping("/save")
    public String saveTimetable(@Valid @ModelAttribute("timetable") Timetable timetable, BindingResult result) {
        if (result.hasErrors()) {
            return "add_timetable";
        }
        service.save(timetable);
        return "redirect:/timetable";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Timetable timetable = service.get(id);
        model.addAttribute("timetable", timetable);
        return "edit_timetable";
    }

    @PostMapping("/update")
    public String updateTimetable(@Valid @ModelAttribute("timetable") Timetable timetable, BindingResult result) {
        if (result.hasErrors()) {
            return "edit_timetable";
        }
        service.save(timetable);
        return "redirect:/timetable";
    }

    @GetMapping("/delete/{id}")
    public String deleteTimetable(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/timetable";
    }
}
