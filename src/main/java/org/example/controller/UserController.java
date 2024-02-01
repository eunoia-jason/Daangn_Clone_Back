package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.domain.User;
import org.example.dto.request.UserRequest;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/daangn/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public void userCreate (@RequestBody UserRequest userRequest) {
        userService.create(userRequest);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> userRead(@PathVariable String email) {
        User user = userService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<User> userUpdate(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User updatedUser = userService.update(id, userRequest.getRegion());
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> userDelete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<?> userUpdate(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        User user = userService.update(id, userRequest.getRegion());

        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "User not update: " + user);
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void userDelete(@PathVariable Long id) {
        userService.delete(id);
    }
}