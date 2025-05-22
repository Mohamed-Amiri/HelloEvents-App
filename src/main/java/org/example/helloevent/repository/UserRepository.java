package org.example.helloevent.repository;

import java.util.Optional;
import org.example.helloevent.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}