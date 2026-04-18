package lk.ijse.authservice.service.impl;

import lk.ijse.authservice.dto.LoginDto;
import lk.ijse.authservice.dto.UserDto;
import lk.ijse.authservice.entity.User;
import lk.ijse.authservice.repository.UserRepository;
import lk.ijse.authservice.service.AuthService;
import lk.ijse.authservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder;

    public String register(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        // Password එක Encrypt කරලා තමයි Save කරන්නේ
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return "User Registered Successfully!";
    }

    public String login(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Password එක match වෙනවද බලන්න
        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // වැඩේ හරි නම්, Token එක හදන්න
        return jwtUtil.generateToken(user.getName());
    }
}
