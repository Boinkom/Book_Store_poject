package org.example.Controller;

import lombok.extern.slf4j.Slf4j;
import org.example.storage.BookStorage;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Slf4j
@Controller
public class AppController {

    @GetMapping("/")
    public String home() {
        return "login-form";
    }

    @GetMapping("/main_store_book")
    public String entry(@RequestHeader("Authorization") String authorizationHeader, Model model) {
               return "main-form";
    }

}
