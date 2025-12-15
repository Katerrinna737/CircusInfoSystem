// конвертирует дату представления в строку, чтобы Spring ог правильно ее отобразить в редактировании
package circus.kate.com.circusinfosystem.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry; // позволяет настраивать преобразование типов (строка<->дата)
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(LocalDate.class, String.class, localDate -> { //дату в строку
            if (localDate == null) {
                return "";
            }
            return localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        });

        registry.addConverter(String.class, LocalDate.class, text -> { // строку в дату
            if (text == null || text.trim().isEmpty()) {
                return null;
            }
            return LocalDate.parse(text.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        });
    }
}