package org.example.service;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.dto.request.UserRequest;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void create( UserRequest request ){
        User user = User.builder()
                .id(request.getId())
                .name(request.getName())
                .email(request.getEmail())
                .image(request.getImage())
                .temperature(request.getTemperature())
                .region(request.getRegion())
                .regDate(request.getRegDate())
                .recentLogin(request.getRecentLogin())
                .build();
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User update(Long id, String region) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setRegion(region);
            userRepository.save(user);
        }
        return user;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}