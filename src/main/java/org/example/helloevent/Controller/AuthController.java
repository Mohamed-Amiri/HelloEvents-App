package org.example.helloevent.Controller;

import org.example.helloevent.Dto.LoginDTO;
import org.example.helloevent.Dto.UserDTO;
import org.example.helloevent.Entity.Admin;
import org.example.helloevent.Entity.Client;
import org.example.helloevent.Entity.User;
import org.example.helloevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping({"/auth"})
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthController() {
    }

    @PostMapping({"/register"})
    public String register(@RequestBody UserDTO userDTO) {
        User user;
        if (userDTO.getRole().equalsIgnoreCase("ADMIN")) {
            user = new Admin();
        } else {
            user = new Client();
        }

        user.setEmail(userDTO.getEmail());
        user.setPassword(this.passwordEncoder.encode(userDTO.getPassword()));
        if (user instanceof Admin admin) {
            admin.setName(userDTO.getName());
            this.userRepository.save(admin);
        } else if (user instanceof Client client) {
            client.setName(userDTO.getName());
            this.userRepository.save(client);
        }

        return "User registered successfully as " + userDTO.getRole();
    }
    @PostMapping("/login")
    public String login(@RequestBody LoginDTO loginDTO) {
        Optional<User> user = userRepository.findByEmail(loginDTO.getEmail());

        if (user == null) {
            return "User not found";
        }

        if (!passwordEncoder.matches(loginDTO.getPassword(), user.get().getPassword())) {
            return "Invalid password";
        }

        return "Login successful. Welcome " + user.get().getEmail() + "!";
    }

}
