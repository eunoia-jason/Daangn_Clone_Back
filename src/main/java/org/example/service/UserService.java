package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.dto.request.UserRequest;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // User Build 후 Create
    public User create( UserRequest request ){
        User user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .image(request.getImage())
                .temperature(request.getTemperature())
                .region(request.getRegion())
                .build();
        return userRepository.save(user);
    }

    // Email로 User find
    public User findByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            User user = userRepository.findByEmail(email);
            user.setRecentLogin(new Timestamp(System.currentTimeMillis()));
            return userRepository.save(user);
        }
        else {
            return null;
        }
    }

    // User Update
    public User update(Long id, String region) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not Found with ID: " + id));
        user.setRegion(region);
        return userRepository.save(user);
    }

    // User Delete
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}