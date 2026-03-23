package com.example.tasktracker.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.tasktracker.DTO.TaskDto;
import com.example.tasktracker.Entity.Project;
import com.example.tasktracker.Entity.Task;
import com.example.tasktracker.Entity.User;
import com.example.tasktracker.Entity.Enums.Priority;
import com.example.tasktracker.Entity.Enums.TaskStatus;
import com.example.tasktracker.Repository.ProjectRepository;
import com.example.tasktracker.Repository.TaskRepository;
import com.example.tasktracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaskService {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    //ADMIN
    public List<Task> findAllTasks(){
        return taskRepository.findAll();
    }
    //ADMIN

    public List<TaskDto> getAllProjecTasks(Long projectId){
        Project project = projectRepository.findById(projectId).orElseThrow(()-> new RuntimeException("Project undefinde"));
        User user = userRepository.findById(project.getOwnerId()).orElseThrow();
        List<Task> tasks = taskRepository.findByProjectId(projectId);
        return tasks.stream().map(task -> new TaskDto(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), project.getName(),user.getName())).toList();
    }

    public TaskDto createTask(Task task){

        User user = userRepository.findById(task.getAssigneeId()).orElseThrow(()-> new RuntimeException("User whith id: "+task.getAssigneeId()+" undefinde"));
        Project project = projectRepository.findById(task.getProjectId()).orElseThrow(()-> new RuntimeException("Project with id: "+task.getProjectId()+" undefinde"));
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.TODO);
        }
        if (task.getPriority() == null) {
            task.setPriority(Priority.MEDIUM);
        }
        taskRepository.save(task);

        log.info("Project created");

        TaskDto dto = new TaskDto(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), project.getName(), user.getName());

        return dto;
        

    }


    public TaskDto delTask(Long id){
        Task task = taskRepository.findById(id).orElseThrow(()-> new RuntimeException("UNDEFINDE Task"));
        User user = userRepository.findById(task.getAssigneeId()).orElseThrow(()-> new RuntimeException("User whith id: "+task.getAssigneeId()+" undefinde"));
        Project project = projectRepository.findById(task.getProjectId()).orElseThrow(()-> new RuntimeException("Project with id: "+task.getProjectId()+" undefinde"));
        TaskDto dto = new TaskDto(task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), project.getName(), user.getName());
        taskRepository.deleteById(id);
        log.info("Task deleted");
        return dto;
    }
}
