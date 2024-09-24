package com.example.taskmanagementsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
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
    private String email;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    @Builder.Default
    private List<Role> roles = new ArrayList<>();

    @Column(name = "password", nullable = false)
    private String password;

    public void addTaskForAuthor(Tasks task) {
        if (task == null) authorship = new ArrayList<>();
        authorship.add(task);
    }

    public void addTaskForExecutors(Tasks task) {
        if (task == null) executors = new ArrayList<>();
        executors.add(task);
    }

}
