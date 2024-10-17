package org.example.Controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.models.User;
import org.example.service.UserService;
import org.example.storage.BookStorage;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Slf4j
@Controller
@AllArgsConstructor
public class AppController {
    private final UserService userService;

    @GetMapping("/")
    public String home() {
        return "login-form";
    }

    @GetMapping("/main-form")
    public String showMainForm(Model model) {
        model.addAttribute("books", BookStorage.getBooks());
        return "main-form";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("books", BookStorage.getBooks());
        return "main-form";
    }

    @GetMapping("/lk")
    public String showLk(Authentication authentication, Model model) {
        return "lk";
    }

    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        return "registration-form";
    }

    @PostMapping("/register-user")
    public String create(@RequestParam String username, @RequestParam String email, @RequestParam String password, Model model) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(UUID.randomUUID().toString());
        userDTO.setUsername(username);
        userDTO.setEmail(email);
        userDTO.setPassword(password);
        userService.addUser(userDTO);
        return "redirect:/";
    }
}
