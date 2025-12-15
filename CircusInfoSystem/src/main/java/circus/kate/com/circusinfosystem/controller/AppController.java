package circus.kate.com.circusinfosystem.controller;

import java.util.List;

import circus.kate.com.circusinfosystem.model.Performer; //импортируем модель класса артиста
import circus.kate.com.circusinfosystem.service.PerformerService; // импортируем логику работы с артистами
import jakarta.validation.Valid; // активирует валидацию данных, например, при сохранении нового артиста
import org.springframework.beans.factory.annotation.Autowired; //связываем все зависимости
import org.springframework.stereotype.Controller; // аннотация, позволяющая распознать класс как управляющий, т.е. в нем указываем адреса страниц, что откуда выводится и т.д
import org.springframework.ui.Model; //необходимо для взаимодествия контроллера и конфигуратора ModelViewController, а также для добавления всех эл-тов в веб-интерфейс
import org.springframework.validation.BindingResult; //для проверки ошибок валидации (не допускать пустые значения)
import org.springframework.web.bind.annotation.*; // обработка http запросов
import org.springframework.web.servlet.ModelAndView; // метод, позволяющий указывать названия html-страниц, которые подвязываем к контроллеру

@Controller
public class AppController {

    @Autowired
    private PerformerService service;

    @RequestMapping("/") // "/" - открывает главную страницу
    public String viewHomePage(Model model,
                               @RequestParam(name="keyword", required = false) String keyword,  //передаем параметр поисковой строки keyword
                               @RequestParam(name="sortField", required = false, defaultValue = "id") String sortField, // тут задаем параметры сортировки
                               @RequestParam(name="sortDir", required = false, defaultValue = "asc") String sortDir
    ) {
        List<Performer> listPerformers;

        if (keyword != null && !keyword.trim().isEmpty()) {
            listPerformers = service.listAll(keyword); //поиск
        }
        else {
            listPerformers = service.findAllSorted(sortField, sortDir); //сортировка
        }

        model.addAttribute("listPerformers", listPerformers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        return "index"; // главная страница
    }

    @RequestMapping("/new") //контроллер по добавлению артиста
    public String showNewPerformerForm(Model model) {
        Performer performer = new Performer();
        model.addAttribute("performer", performer);
        return "new_performer"; //возвращаем страницу
    }

    @RequestMapping(value = "/save-new", method = RequestMethod.POST) //метод сохранения новых артистов
    public String saveNewPerformer(@Valid @ModelAttribute("performer") Performer performer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) { // если есть ошибки - возвращаем страницу формы
            return "new_performer";
        }
        service.save(performer);
        return "redirect:/"; // после нажатия на кнопку сохранить выполняется сохранение и редирект на главную страницу
    }

    @RequestMapping(value = "/save-edit", method = RequestMethod.POST) //метод сохранения отредактированных артистов
    public String saveEditedPerformer(@Valid @ModelAttribute("performer") Performer performer, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit_performer";
        }
        service.save(performer);
        return "redirect:/"; // после нажатия на кнопку сохранить выполняется сохранение и редирект на главную страницу
    }

    @RequestMapping("/edit/{id}") //контроллер редактирования артистов по id
    public ModelAndView showEditPerformerForm(@PathVariable(name = "id") Long id) { //параметр передаем из браузерной строки
        ModelAndView mav = new ModelAndView("edit_performer");
        Performer performer = service.get(id);
        mav.addObject("performer", performer);
        return mav; //возвращаем страницу с данными об актере по id
    }

    @RequestMapping("/delete/{id}")
    public String deletePerformer(@PathVariable(name = "id") Long id) {
        service.delete(id);
        return "redirect:/";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "about";
    }
}
