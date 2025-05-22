package org.example.helloevent.service;

import org.example.helloevent.Entity.Admin;
import org.example.helloevent.Entity.User;
import org.example.helloevent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService() {
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = this.userRepository.findByEmail(email);
        return org.springframework.security.core.userdetails.User.builder().username(user.getEmail()).password(user.getPassword()).roles(new String[]{user instanceof Admin ? "ADMIN" : "CLIENT"}).build();
    }
}
