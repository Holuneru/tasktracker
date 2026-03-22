package com.example.tasktracker.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasktracker.DTO.ProjectDto;
import com.example.tasktracker.Entity.Project;
import com.example.tasktracker.Service.ProjectService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/ownerId/{ownerId}")
    public List<ProjectDto> getAllProjectsByOwnerId(@PathVariable Long ownerId) {
        return projectService.getAllProjectsByOwnerId(ownerId);
    }
    

    @GetMapping
    public List<ProjectDto> getAllProjectDto() {
        return projectService.getAllProjectDto();
    }
    

    @PostMapping
    public ProjectDto createProject(@RequestBody Project project) {
        return projectService.createProject(project);
    }


    @DeleteMapping("/{id}")
    public ProjectDto delProject(@PathVariable Long id){
        return projectService.delProject(id);
    }

}
