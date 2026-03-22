package com.example.tasktracker.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {

    
    private String name;

    private String description;

    private String ownerNameString;
    
}
