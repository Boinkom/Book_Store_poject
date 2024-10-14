package org.example.Controller;

import org.example.models.BookModels;
import org.example.models.User;
import org.example.storage.UserStorage;
import org.springframework.ui.Model;
import org.example.storage.BookStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.UUID;


@Controller
public class AppController {

    @GetMapping("/")
    public String home() {
        return "login-form";
    }
    @GetMapping("/main_store_book")
    public String entry(Model model) {
     model.addAttribute("books", BookStorage.getBooks());
     return "main-form";
    }

    @PostMapping("/login")
    public String login() {
        return "ok";
    }

}
