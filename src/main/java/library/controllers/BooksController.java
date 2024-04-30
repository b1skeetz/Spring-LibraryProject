package library.controllers;

import jakarta.validation.Valid;
import library.DAO.BookDAO;
import library.DAO.PersonDAO;
import library.Models.Book;
import library.Models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("books", bookDAO.getAll());
        return "books/index";
    }

    @GetMapping("/{id}") // I am here
    public String book(@PathVariable("id") Long id, Model model, @ModelAttribute("person") Person person) {
        model.addAttribute("book", bookDAO.getElementById(id));
        model.addAttribute("people", personDAO.getAll());
        model.addAttribute("personWithBook", personDAO.getPersonWithBook(id).stream().findAny().orElse(new Person()));
        model.addAttribute("condition", personDAO.getPersonWithBook(id).isPresent());
        return "books/book";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("book", bookDAO.getElementById(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult,
                         @PathVariable("id") Long id) {
        if(bindingResult.hasErrors()){
            return "books/edit";
        }
        bookDAO.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/add";
    }

    @PostMapping
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "books/add";
        }
        bookDAO.save(book);
        return "redirect:/books";
    }

    @PostMapping("/{id}/add")
    public String addReader(@ModelAttribute("person") Person person, @PathVariable("id") Long id){
        bookDAO.addReader(id, person.getPersonId());
        return "redirect:/books/" + id;
    }

    @PostMapping("/{id}/remove")
    public String removeReader(@PathVariable("id") Long id){
        bookDAO.removeReader(id);
        return "redirect:/books/" + id;
    }
}