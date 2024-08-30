package com.example.taskmanagementsystem.service;

import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.web.model.TaskFilterAuthor;

import java.util.List;

public interface TasksService {


    List<Tasks> filterByAuthor(TaskFilterAuthor filter);

    List<Tasks> findAll();

    Tasks findById(Long id);

    Tasks save(Tasks task);

    Tasks update(Tasks task);

    void deleteById(Long id);

    void deleteById(List<Long> ids);
}
