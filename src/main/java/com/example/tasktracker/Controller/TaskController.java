package com.example.tasktracker.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasktracker.DTO.TaskDto;
import com.example.tasktracker.Entity.Task;
import com.example.tasktracker.Service.TaskService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @PostMapping
    public TaskDto createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }
    
    @DeleteMapping("/{id}")
    public TaskDto delTask(@PathVariable Long id){
        return taskService.delTask(id);
    }
}
