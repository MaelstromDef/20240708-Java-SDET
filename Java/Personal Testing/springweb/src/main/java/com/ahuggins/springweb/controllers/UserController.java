package com.ahuggins.springweb.controllers;

import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.springweb.dtos.UserDto;
import com.ahuggins.springweb.models.User;
import com.ahuggins.springweb.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;



/*
 * Controller vs RestController
 * 
 *      @RestController implicity adds @ResponseBody to every method in the class
 *      @Controller - you have to include it on every method
 *      @ResponseBody will tell the controller that the object returned is serialized into
 *          JSON inside the body of the HTTP response.
 *      @RequestMapping will allow us to map all of our requests to a specific path
*/

// localhost:8080/users/helloWorld

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping("/helloWorld")
    public String helloWorld(){
        return "Hello World!";
    }

    @GetMapping
    public List<UserDto> getAllUsers(){
        return service.findAllUsers();
    }

    @GetMapping("/firstName")
    public List<User> getUserByFirstName(@RequestParam String firstName) {
        return service.findUserByFirstName(firstName);
    }
    
    @GetMapping("/user/{id}")
    public User getMethodName(@PathVariable long id) {
        return service.findUserById(id);
    }
    
    @PostMapping("/user")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User createUser = service.createUser(user);

        ResponseEntity<User> entity = new ResponseEntity<User>(createUser, HttpStatus.CREATED);
        return entity;
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user){
        User updatedUser = service.updateUser(id, user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable long id){
        User user = service.deleteUser(id);

        if(user == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}