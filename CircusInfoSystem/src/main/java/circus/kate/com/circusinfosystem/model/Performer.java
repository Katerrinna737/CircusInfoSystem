// тут создаем класс артистов и соответствующую таблицу
package circus.kate.com.circusinfosystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue; // работа со столбцами
import jakarta.persistence.GenerationType; //отвечает за тип данных и перечисление
import jakarta.persistence.Id; //отвечает за определние первичного ключа объекта
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; //эти 2 аннотации, чтобы не добавлять пустые значения при добавлении артистов


@Entity
public class Performer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //строки будут идти по порядку
    private Long id;

    @NotBlank(message = "Имя артиста обязательно для заполнения")
    private String fullName;

    @NotBlank(message = "Специальность обязательна для заполнения")
    private String speciality;

    @NotNull(message = "Стаж обязателен для заполнения")
    private Float yearsExperience;


    public Performer() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public Float getYearsExperience() {
        return yearsExperience;
    }

    public void setYearsExperience(Float yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

}