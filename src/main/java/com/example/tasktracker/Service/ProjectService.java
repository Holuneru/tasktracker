package com.example.tasktracker.Service;

import org.springframework.stereotype.Service;

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

}
