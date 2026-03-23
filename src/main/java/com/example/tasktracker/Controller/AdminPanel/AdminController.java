package com.example.tasktracker.Controller.AdminPanel;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasktracker.Entity.Project;
import com.example.tasktracker.Entity.Task;
import com.example.tasktracker.Entity.User;
import com.example.tasktracker.Service.ProjectService;
import com.example.tasktracker.Service.TaskService;
import com.example.tasktracker.Service.UserService;

import lombok.RequiredArgsConstructor;



@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin")
public class AdminController {

    private final UserService userService;
    private final ProjectService projectService;
    private final TaskService taskService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/projects")
    public List<Project> getAllProjects() {
        return projectService.finaAllProjects();
    }
    
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.findAllTasks();
    }
    
    
}