package com.example.tasktracker.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasktracker.DTO.ProjectDto;
import com.example.tasktracker.Entity.Project;
import com.example.tasktracker.Service.ProjectService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;


    @PostMapping()
    public ProjectDto createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }
    

}
