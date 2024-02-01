package org.example.domain;

import lombok.*;
import org.springframework.lang.Nullable;
import java.sql.Timestamp;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private float temperature;
    private String region;
    @Nullable
    private String image;
    @Column(name = "reg_date", updatable = false)
    private Timestamp regDate;
    @Column(name = "recent_login")
    private Timestamp recentLogin;

    @PrePersist
    protected void onCreate() {
        regDate = new Timestamp(System.currentTimeMillis());
        recentLogin = new Timestamp(System.currentTimeMillis());
    }

}