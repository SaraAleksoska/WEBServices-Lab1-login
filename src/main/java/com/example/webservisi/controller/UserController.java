package com.example.webservisi.controller;

import com.example.webservisi.domain.User;
import com.example.webservisi.domain.UserReg;
import com.example.webservisi.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping
    public ResponseEntity<Set<User>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User userInput) {
        return ResponseEntity.ok(userService.login(userInput.getUsername(), userInput.getPassword()));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserReg userReg) throws Exception {
        userService.register(userReg.getUsername(), userReg.getFirstPassword(), userReg.getSecondPassword());
        return ResponseEntity.ok("Successful registration!");
    }

    @DeleteMapping
    public ResponseEntity<String> unregister(@RequestBody UserReg userReg) throws Exception {
        userService.unregister(userReg.getUsername(), userReg.getFirstPassword(), userReg.getSecondPassword());
        return ResponseEntity.ok("Successful removal!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex, WebRequest req) {
        return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
    }

}
