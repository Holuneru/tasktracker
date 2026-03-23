package com.example.tasktracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tasktracker.Entity.Task;

import java.util.Optional;
import java.util.List;




@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    Optional<Task> findByProjectId(Long projectId);

    List<Task> findByProjectIdList(Long projectId);

    Optional<Task> findByAssigneeId(Long assigneeId);
}
