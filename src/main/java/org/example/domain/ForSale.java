package org.example.domain;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ForSale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private int price;
    private int interest;
    private int view;
    private String description;
    @Nullable
    private String image;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "reg_date", updatable = false)
    private Timestamp regDate;
    @Column(name = "mod_date")
    private Timestamp modDate;

    @PrePersist
    protected void onCreate() {
        regDate = new Timestamp(System.currentTimeMillis());
    }

    @PreUpdate
    protected void onUpdate() {
        modDate = new Timestamp(System.currentTimeMillis());
    }
}
