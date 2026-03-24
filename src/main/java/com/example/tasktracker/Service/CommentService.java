package com.example.tasktracker.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.tasktracker.DTO.CommentDto;
import com.example.tasktracker.Entity.Comment;
import com.example.tasktracker.Entity.Task;
import com.example.tasktracker.Entity.User;
import com.example.tasktracker.Repository.CommentRepository;

import com.example.tasktracker.Repository.TaskRepository;
import com.example.tasktracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentDto addCommentForTask(Comment comment){
        // 1. Сначала проверяем, существуют ли задача и пользователь (Fail Fast)
        var task = taskRepository.findById(comment.getTaskId())
            .orElseThrow(() -> new RuntimeException("Task not found"));
        var user = userRepository.findById(comment.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));
        comment.setCreatedAt(LocalDateTime.now());
        
        commentRepository.save(comment);

        log.info("Comment saved");

        return new CommentDto(
            task.getTitle(),
            user.getName(),
            comment.getText(),
            comment.getCreatedAt()
        );

    }

    public List<CommentDto> getAllCommentsForTask(Long taskId){
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found with id: " + taskId));
        List<Comment> comments = commentRepository.findByTaskId(taskId);

        return comments.stream()
            .map(comment -> {
                String userName = userRepository.findById(comment.getUserId()).map(User::getName).orElse("Unknown user");
                return new CommentDto(
                    task.getTitle(),
                    userName,
                    comment.getText(),
                    comment.getCreatedAt()
                );
            }).toList();
        
    }


    
}
