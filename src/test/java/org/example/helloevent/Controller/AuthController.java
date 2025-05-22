package org.example.helloevent.Controller;

import org.example.helloevent.Dto.UserDTO;
import org.example.helloevent.Entity.Admin;
import org.example.helloevent.Entity.Client;
import org.example.helloevent.Entity.User;
import org.example.helloevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String register(@RequestBody UserDTO userDTO) {
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
            userRepository.save(admin);
        } else if (user instanceof Client client) {
            client.setName(userDTO.getName());
            userRepository.save(client);
        }

        return "User registered successfully as " + userDTO.getRole();
    }
}
