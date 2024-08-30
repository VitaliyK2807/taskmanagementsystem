package com.example.taskmanagementsystem.repositories;

import com.example.taskmanagementsystem.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findAllByAuthorId(long id);
}
