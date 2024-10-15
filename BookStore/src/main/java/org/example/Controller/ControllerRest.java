package org.example.Controller;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.LoginPasswordDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ControllerRest {

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> login(@RequestBody LoginPasswordDto loginPasswordDto) {
        return ResponseEntity.ok("ok");
    }
}
