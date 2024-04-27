package library.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Person {
    private long id;

    @NotEmpty(message = "Поле ФИО не может быть пустым")
    @Size(min = 5, max = 100, message = "ФИО должно быть больше 5 и менее 100 символов")
    private String selfIdentity;

    @NotEmpty(message = "Поле год рождения не может быть пустым")
    @Min(value = 1900, message = "Год рождения должен быть больше 1900 года")
    @Max(value = 2024, message = "Год рождения должен быть меньше 2024 года")
    private int yearOfBirth;

    private List<Book> books = new ArrayList<>();

    public Person(){

    }

    public Person(String FIO, int yearOfBirth) {
        this.selfIdentity = FIO;
        this.yearOfBirth = yearOfBirth;
    }
}
