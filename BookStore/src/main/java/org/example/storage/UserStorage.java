package org.example.storage;

import lombok.RequiredArgsConstructor;
import org.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class UserStorage {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String, User> userMap;


    public UserStorage(BCryptPasswordEncoder bCryptPasswordEncoder, Map<String, User> userMap) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userMap = userMap;
        this.userMap.put("admin", new User("admin", bCryptPasswordEncoder.encode("admin"), List.of("ADMIN")));
    }

    public User findUserByName(String username) {
        return userMap.get(username);
    }

    public void addUser(User user) {
        userMap.put(user.getUsername(), user);
    }
}
