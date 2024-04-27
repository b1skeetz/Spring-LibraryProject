package library.Models;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    private long id;

    private long personId;

    @NotEmpty(message = "Поле название книги не может быть пустым")
    @Size(min = 5, max = 50, message = "Название книги должно быть длиннее 5 и короче 50 символов")
    private String name;

    @NotEmpty(message = "Поле автор книги не может быть пустым")
    @Size(min = 5, max = 100, message = "Автор книги должен быть длиннее 5 и короче 100 символов ")
    private String author;

    @NotEmpty(message = "Поле год издания не может быть пустым")
    @Min(value = 0, message = "Год издания не может быть меньше 0")
    @Max(value = 2024 , message = "Год издания не может быть больше 2024")
    private int yearOfRelease;

    public Book(){

    }

    public Book(String name, String author, int yearOfRelease) {
        this.name = name;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
    }
}
