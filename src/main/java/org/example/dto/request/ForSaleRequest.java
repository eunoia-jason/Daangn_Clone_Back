package org.example.dto.request;

import lombok.Data;

@Data
public class ForSaleRequest {
    private Long id;
    private String title;
    private String category;
    private int price;
    private String description;
    private String image;
}