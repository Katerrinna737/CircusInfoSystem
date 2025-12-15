// класс, отвечающий за запуск приложения

package circus.kate.com.circusinfosystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class PerformerManagerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) { //запуск приложения
        SpringApplication.run(PerformerManagerApplication.class, args);
    }
}
