package circus.kate.com.circusinfosystem.controller;

import circus.kate.com.circusinfosystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    // отображение страницы входа
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new circus.kate.com.circusinfosystem.model.User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(circus.kate.com.circusinfosystem.model.User user, Model model) {
        try {
            // вызываем сервис, который хэширует пароль и сохраняет
            userService.registerUser(user);
            // после успешной регистрации - на страницу входа
            return "redirect:/login?registered";
        } catch (RuntimeException e) {
            // Если возникло исключение (например, уже существует)
            model.addAttribute("user", user); // возвращаем заполненную форму
            model.addAttribute("error", e.getMessage()); // передаем сообщение об ошибке
            return "register"; // возвращаемся к форме регистрации
        }
    }
}