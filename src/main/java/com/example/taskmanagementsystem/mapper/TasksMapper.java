package com.example.taskmanagementsystem.mapper;

import com.example.taskmanagementsystem.mapper.delegate.TaskMapperDelegate;
import com.example.taskmanagementsystem.model.Tasks;
import com.example.taskmanagementsystem.web.model.TaskRequest;
import com.example.taskmanagementsystem.web.model.TaskResponse;
import com.example.taskmanagementsystem.web.model.TasksListResponse;
import com.example.taskmanagementsystem.web.model.UpsertTaskRequest;
import org.mapstruct.*;

import java.util.List;

@DecoratedWith(TaskMapperDelegate.class)
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TasksMapper {

    Tasks requestToTask(UpsertTaskRequest request);

    Tasks requestToTask(TaskRequest request);

    @Mapping(source = "taskId", target = "id")
    Tasks requestToTask(Long taskId, UpsertTaskRequest request);

    TaskResponse taskToResponse(Tasks task);

    List<TaskResponse> taskListToResponseList(List<Tasks> tasks);

    default TasksListResponse taskListToTaskListResponse(List<Tasks> tasks) {
        return TasksListResponse.builder()
                .tasks(taskListToResponseList(tasks))
                .build();
    }
}
