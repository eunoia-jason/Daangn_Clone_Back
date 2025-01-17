package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.dto.request.UserRequest;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/daangn/user")
public class UserController {
    private final UserService userService;

    // User Create
    @PostMapping("/create")
    public ResponseEntity<User> userCreate (@RequestBody UserRequest userRequest) {
        User user = userService.create(userRequest);
        return ResponseEntity.ok(user);
    }

    // Email로 유저 Read
    @GetMapping("/{email}")
    public ResponseEntity<User> userRead(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    // 현재 로그인된 유저의 지역 Update
    @PatchMapping("/update/{id}")
    public ResponseEntity<User> userUpdate(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User updatedUser = userService.update(id, userRequest.getRegion());
        return ResponseEntity.ok(updatedUser);
    }

    // 현재 로그인된 유저 Delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> userDelete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }
}