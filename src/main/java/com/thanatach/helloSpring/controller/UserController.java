package com.thanatach.helloSpring.controller;

import com.thanatach.helloSpring.model.User;
import com.thanatach.helloSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllUsers")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") int id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElse(null);
    }

    @GetMapping("/GetUserbyName/{name}")
    public User GetUserbyName(@PathVariable("name") String name) {
        return userRepository.findByName(name);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @DeleteMapping("/deleteUser/{id}")
    public void deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping("/deleteUserByName/{name}")
    public User deleteUserByName(@PathVariable("name") String name) {
        User user = userRepository.findByName(name);
        userRepository.delete(user);
        return user;
    }








}
