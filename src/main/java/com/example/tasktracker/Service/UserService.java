package com.example.tasktracker.Service;

import org.springframework.stereotype.Service;

import com.example.tasktracker.DTO.UserDto;
import com.example.tasktracker.Entity.User;
import com.example.tasktracker.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    

    public UserDto registerUser(User user){
        userRepository.save(user);
        log.info("User created!");
        UserDto dto = new UserDto(user.getName(), user.getEmail());
        return dto;

    }

    
}
