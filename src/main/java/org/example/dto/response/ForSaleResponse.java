package org.example.dto.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Data
public class ForSaleResponse {
    private Long id;
    private String title;
    private String category;
    private int price;
    private int interest;
    private int view;
    private String description;
    private String image;
    private String imageUrl;
    private Timestamp regDate;
    private UserIdDTO userId;

    @Getter
    @Setter
    public static class UserIdDTO {
        private Long userId;
    }
}
