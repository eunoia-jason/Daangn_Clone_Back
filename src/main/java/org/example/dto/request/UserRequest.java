package org.example.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String email;
    private float temperature;
    private String region;
    private String image;
    private List<Long> interests;
}