package org.example.helloevent.controller;

import org.example.helloevent.Dto.LoginDTO;
import org.example.helloevent.Dto.UserDTO;
import org.example.helloevent.entity.Admin;
import org.example.helloevent.entity.Client;
import org.example.helloevent.entity.User;
import org.example.helloevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.example.helloevent.config.JwtUtil;
import java.util.HashMap;
import java.util.Map;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Email is already registered.");
        }

        User user;
        if (userDTO.getRole().equalsIgnoreCase("ADMIN")) {
            user = new Admin();
        } else {
            user = new Client();
        }

        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        if (user instanceof Admin admin) {
            admin.setName(userDTO.getName());
        } else if (user instanceof Client client) {
            client.setName(userDTO.getName());
        }

        userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User registered successfully as " + userDTO.getRole());
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());

        Map<String, String> response = new HashMap<>();

        if (user.isEmpty()) {
            response.put("message", "User not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            response.put("message", "Invalid password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        String token = jwtUtil.generateToken(user.get().getEmail());
        response.put("token", token);
        response.put("message", "Login successful. Welcome " + user.get().getEmail() + "!");
        return ResponseEntity.ok(response);
    }
}
