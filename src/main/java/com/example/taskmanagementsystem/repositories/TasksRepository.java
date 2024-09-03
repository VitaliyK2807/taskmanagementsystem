package com.example.taskmanagementsystem.repositories;

import com.example.taskmanagementsystem.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long>, JpaSpecificationExecutor<Tasks> {

}
