package circus.kate.com.circusinfosystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "timetable")
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Дата обязательна")
    @Column(nullable = false)
    private LocalDate date;

    @NotNull(message = "Время обязательно")
    @Column(nullable = false)
    private LocalTime time;

    @NotNull(message = "Длительность обязательна")
    @Column(nullable = false)
    private Integer duration; // в минутах

    @NotNull(message = "Стоимость обязательна")
    @Column(nullable = false)
    private Double cost; // в рублях

    public Timetable() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
