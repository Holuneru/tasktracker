package com.example.tasktracker.Service;

import java.util.List;


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

    public List<User> getAllUsersFull(){
        return userRepository.findAll();
    }

    public List<UserDto> getAllUsers(){
        
    List<User> users = userRepository.findAll();
        return users.stream().map(user -> new UserDto(user.getName(),user.getEmail())).toList();

    }

    public User getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(()-> new RuntimeException("User undefinde by id: "+ id));
        return user;
    }

    public UserDto registerUser(User user){
        userRepository.save(user);
        log.info("User created!");
        UserDto dto = new UserDto(user.getName(), user.getEmail());
        return dto;

    }

    public UserDto loginUser(User user){

        User userAuth = userRepository.findByName(user.getName()).orElseThrow(()-> new RuntimeException("incorrect user_name"));
        if (!userAuth.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("incorrect user_password");
        }
        // if (!userAuth.getEmail().equals(user.getEmail())) {
        //     throw new RuntimeException("incorrect user_email");
        // }
        
        UserDto dto = new UserDto(user.getName(), user.getEmail());
        log.info("User logged");
        return dto;

    }

    
}
