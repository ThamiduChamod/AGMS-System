package lk.ijse.authservice.service;

import lk.ijse.authservice.dto.LoginDto;
import lk.ijse.authservice.dto.UserDto;

public interface AuthService {
    String register(UserDto userDto);
    String login(LoginDto loginDto);
}
