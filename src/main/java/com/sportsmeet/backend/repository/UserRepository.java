package com.sportsmeet.backend.repository;

import com.sportsmeet.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query if needed

    Optional<User> findByEmail(String email);
//    User findByEmail(String email); // Optional: for login or lookup
}
