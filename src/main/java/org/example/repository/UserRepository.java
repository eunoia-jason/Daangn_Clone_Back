package org.example.repository;

import org.example.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Email로 User find
    User findByEmail(String email);
    // Email을 가진 유저 있는지 탐색
    Boolean existsByEmail(String email);
}