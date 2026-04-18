package lk.ijse.authservice.controller;

import lk.ijse.authservice.dto.LoginDto;
import lk.ijse.authservice.dto.UserDto;
import lk.ijse.authservice.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(authService.register(userDto));
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginRequest) {
        String token = authService.login(loginRequest);
        return ResponseEntity.ok(token); // මෙතනින් තමයි Token එක Frontend එකට යන්නේ
    }
}
