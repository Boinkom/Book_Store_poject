package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginPasswordDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
