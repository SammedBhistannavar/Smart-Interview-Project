package com.Smart.Interview.Prep.Platform.entity;

import com.Smart.Interview.Prep.Platform.entity.Enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role; // USER or ADMIN

    private String phone;
    private String bio;
    private String skills;

    private String resumeUrl;

}
