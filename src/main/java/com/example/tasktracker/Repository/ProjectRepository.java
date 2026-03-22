package com.example.tasktracker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.tasktracker.Entity.Project;

import java.util.Optional;


@Repository
public interface ProjectRepository extends JpaRepository<Project, Long>{
    Optional<Project> findByOwnerId(Long ownerId);
    
}
