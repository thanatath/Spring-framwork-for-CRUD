package com.thanatach.helloSpring.controller;

import com.thanatach.helloSpring.exception.ApiException;
import com.thanatach.helloSpring.exception.BaseException;
import com.thanatach.helloSpring.model.User;
import com.thanatach.helloSpring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() throws BaseException{
        List<User> result = userRepository.findAll();
        if (result.isEmpty()) throw ApiException.nullResult();
        return ResponseEntity.ok(result);

    }

    @GetMapping("/getUserById/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") int id) throws BaseException{
        if(id < 0) throw ApiException.invalidValue();
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) throw ApiException.nullResult();
        return ResponseEntity.ok(result.orElse(null));
    }

    @GetMapping("/GetUserbyName/{name}")
    public ResponseEntity<User> GetUserbyName(@PathVariable("name") String name) throws BaseException{
        if(name == null) throw ApiException.nullValue();
        User result = userRepository.findByName(name);
        if (result == null) throw ApiException.nullResult();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/addUser")
    public User addUser(@RequestBody User user) throws BaseException{
        if(user.getAge() == null || user.getName() == null || user.getLastname() == null) throw ApiException.nullValue();
        return userRepository.save(user);
    }

    @PutMapping("/updateUser/{id}")
    public Optional<User> updateUser(@RequestBody User user, @PathVariable("id") Integer id) throws ApiException {
        if(id < 0) throw ApiException.invalidValue();
        if(user.getAge() == null || user.getName() == null || user.getLastname() == null) throw ApiException.nullValue();
        Optional<User> result = userRepository.findById(id);
        if (result.isEmpty()) throw ApiException.nullResult();
        result.ifPresent(user1 -> {
            user1.setAge(user.getAge());
            user1.setName(user.getName());
            user1.setLastname(user.getLastname());
            userRepository.save(user1);
        });
        return result;
    }

    @DeleteMapping("/deleteUser/{id}")

    public void deleteUser(@PathVariable("id") Integer id) throws BaseException{
        if(id < 0) throw ApiException.invalidValue();
        userRepository.deleteById(id);
    }

    @DeleteMapping("/deleteUserByName/{name}")
    public Optional<User> deleteUserByName(@PathVariable("name") String name) throws ApiException {
        Optional<User> result = Optional.ofNullable(userRepository.findByName(name));
        if (result == null) throw ApiException.nullResult();
        result.ifPresent(user -> {
            userRepository.delete(user);
        });
        return result;
    }








}
