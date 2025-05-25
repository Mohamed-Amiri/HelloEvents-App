package org.example.helloevent.service;

import org.example.helloevent.Entity.Admin;
import org.example.helloevent.Entity.User;
import org.example.helloevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .map(user -> {
                    String role = user instanceof Admin ? "ADMIN" : "CLIENT";
                    return org.springframework.security.core.userdetails.User.builder()
                            .username(user.getEmail())
                            .password(user.getPassword())
                            .roles(role)
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }

}
