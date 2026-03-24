package com.example.tasktracker.DTO;

import java.time.LocalDateTime;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    
    private String taskNameString;

    
    private String userNameString;

    
    private String text;

    
    private LocalDateTime createdAt;
}
