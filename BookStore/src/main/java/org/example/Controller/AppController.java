package org.example.Controller;

import org.springframework.ui.Model;
import org.example.storage.BookStorage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AppController {

    @GetMapping("/")
    public String home() {
        return "login-form";
    }
    @PostMapping("/main_store_book")
    public String entry(Model model) {
     model.addAttribute("books", BookStorage.getBooks());
     return "main-form";
    }
}
