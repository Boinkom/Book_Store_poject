package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class User {
    private String id;

    private String username;

    private String password;

    private String email;

    private List<String> authorities;
}
