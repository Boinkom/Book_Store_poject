package org.example.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.UserDTO;
import org.example.models.User;
import org.example.storage.UserStorage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserStorage userStorage;

    public User findUserByUsername(String username) {
        return userStorage.findUserByName(username);
    }

    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO.getId(),userDTO.getUsername(), bCryptPasswordEncoder.encode(userDTO.getPassword()),userDTO.getEmail(), List.of("USER"));
        userStorage.addUser(user);
        log.info("Регистрация пользователя: " + userDTO.getUsername());
        return user;
    }
}
