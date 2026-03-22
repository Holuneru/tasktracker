package com.example.tasktracker.Service;

import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tasktracker.DTO.ProjectDto;
import com.example.tasktracker.Entity.Project;
import com.example.tasktracker.Entity.User;
import com.example.tasktracker.Repository.ProjectRepository;
import com.example.tasktracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;


    public List<ProjectDto> getAllProjectDto(){
        List<Project> projects = projectRepository.findAll();
        return projects.stream().map(project -> new ProjectDto(project.getName(), project.getDescription(), userRepository.findById(project.getOwnerId()).orElseThrow().getName())).toList();
    }

    public List<ProjectDto> getAllProjectsByOwnerId(Long ownerId){
        List<Project> projects = projectRepository.findByOwnerId(ownerId);
        return projects.stream().map(project -> new ProjectDto(project.getName(), project.getDescription(), userRepository.findById(project.getOwnerId()).orElseThrow().getName())).toList();
    }

    @Transactional
    public ProjectDto createProject(Project project){



        User user = userRepository.findById(project.getOwnerId()).orElseThrow(()-> new RuntimeException("User with id: "+project.getOwnerId()+" is Undefinde"));
        if (project.getDescription() == null||project.getDescription().isBlank()) {
            project.setDescription("None");
        }

        projectRepository.save(project);
        
        log.info("Project created");
        
        ProjectDto dto = new ProjectDto(project.getName(), project.getDescription(), user.getName());
        return dto;
    }

    public ProjectDto delProject(Long id){
        Project projectOptional = projectRepository.findById(id).orElseThrow(()-> new RuntimeException("This project id undefinde"));
        
        ProjectDto dto = new ProjectDto(projectOptional.getName(), projectOptional.getDescription(), userRepository.findById(projectOptional.getOwnerId()).orElseThrow().getName());
        projectRepository.deleteById(id);
        log.info("Project deleted");
        return dto;
    }


}
