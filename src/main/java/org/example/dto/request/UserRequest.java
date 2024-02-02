package org.example.dto.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRequest {
    private Long id;
    private String name;
    private String email;
    private float temperature = 36.5F;
    private String region = "경상북도 포항시 북구";
    private String image;
    private Timestamp regDate;
    private Timestamp recentLogin;
}