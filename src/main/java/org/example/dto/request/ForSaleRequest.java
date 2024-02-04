package org.example.dto.request;

import lombok.Data;
import org.example.domain.User;

@Data
public class ForSaleRequest {
    private Long id;
    private String title;
    private String category;
    private int price;
    private int interest;
    private int view;
    private String description;
    private String image;
    private Long user;
}