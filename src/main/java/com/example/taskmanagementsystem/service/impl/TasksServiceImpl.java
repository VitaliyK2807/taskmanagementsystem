package com.example.taskmanagementsystem.service.impl;

import com.example.taskmanagementsystem.excepcions.EntityNotFoundException;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.model.User;
import com.example.taskmanagementsystem.repositories.TasksRepository;
import com.example.taskmanagementsystem.repositories.specification.TaskSpecification;
import com.example.taskmanagementsystem.service.TasksService;
import com.example.taskmanagementsystem.service.UserService;
import com.example.taskmanagementsystem.utils.BeanUtils;
import com.example.taskmanagementsystem.web.model.TaskFilterAuthor;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TasksServiceImpl implements TasksService {

    private final TasksRepository tasksRepository;
    private final UserService userService;

    @Override
    public List<Tasks> filterByAuthor(TaskFilterAuthor filter) {
        return tasksRepository.findAll(TaskSpecification.withFilter(filter),
                PageRequest.of(filter.getPageNumber(), filter.getPageSize())).getContent();
    }

    @Override
    public List<Tasks> findAll() {
        return tasksRepository.findAll();
    }

    @Override
    public Tasks findById(Long id) {
        return tasksRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(MessageFormat.format(
                        "Задача с ID {0} не найдена!", id
                )));
    }

    @Override
    @Transactional
    public Tasks save(Tasks task) {
        return tasksRepository.save(task);
    }

    @Override
    @Transactional
    public Tasks update(Tasks task) {
        User author = userService.findById(task.getAuthor().getId());
        User executor = userService.findById(task.getExecutor().getId());
        Tasks existedTask = findById(task.getId());

        BeanUtils.copyNonNullProperties(task, existedTask);

        existedTask.setAuthor(author);
        existedTask.setExecutor(executor);

        return tasksRepository.save(existedTask);
    }

    @Override
    public void deleteById(Long id) {
        tasksRepository.deleteById(id);
    }

    @Override
    public void deleteById(List<Long> ids) {
        tasksRepository.deleteAllById(ids);
    }
}
