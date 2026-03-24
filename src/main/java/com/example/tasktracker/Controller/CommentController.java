package com.example.tasktracker.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tasktracker.DTO.CommentDto;
import com.example.tasktracker.Entity.Comment;
import com.example.tasktracker.Service.CommentService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/comments")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public CommentDto addCommentForTask(@RequestBody Comment comment) {
        return commentService.addCommentForTask(comment);
    }
    
    @GetMapping("task/{taskId}/comments")
    public List<CommentDto> getAllCommentsForTask(@PathVariable Long taskId) {
        return commentService.getAllCommentsForTask(taskId);
    }
    

}

