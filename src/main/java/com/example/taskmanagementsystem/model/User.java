package com.example.taskmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String telephone;

    @OneToMany(mappedBy = "author")
    @ToString.Exclude
    @Builder.Default
    private List<Tasks> authorship = new ArrayList<>();

    @OneToMany(mappedBy = "executor")
    @ToString.Exclude
    @Builder.Default
    private List<Tasks> executors = new ArrayList<>();

    @Column(name = "email", nullable = false)
    private String eMail;

    @Column(name = "password", nullable = false)
    private String password;

}
