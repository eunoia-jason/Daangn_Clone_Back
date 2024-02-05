package org.example.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private float temperature;
    private String region;
    private String image;
    private List<Long> interests;
}