package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.UserDTO;
import org.example.models.User;
import org.example.storage.UserStorage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@AllArgsConstructor
public class UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final UserStorage userStorage;

    public User findUserByUsername(String username) {
        return userStorage.findUserByName(username);
    }

    public User addUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), bCryptPasswordEncoder.encode(userDTO.getPassword()), List.of("USER"));
        userStorage.addUser(user);
        return user;
    }
}
