package com.example.tasktracker.DTO;

import com.example.tasktracker.Entity.Enums.Priority;
import com.example.tasktracker.Entity.Enums.TaskStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    private String title;

    private String description;

    private TaskStatus status;

    private Priority priority;

    private String projectNameString;

    private String assigneeName;
    
}
