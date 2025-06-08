package org.example.helloevent.repository;

import java.util.Optional;
import org.example.helloevent.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}