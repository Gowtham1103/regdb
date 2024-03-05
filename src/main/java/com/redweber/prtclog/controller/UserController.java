package com.redweber.prtclog.controller;

import com.redweber.prtclog.entity.User;
import com.redweber.prtclog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/signup")
    public ResponseEntity<String> createUser(@RequestBody User credentials){
        return userService.createUser(credentials);
    }
    @PostMapping("/signin")
    public ResponseEntity<String> checkUser(@RequestBody User credentials){
        return userService.checkUser(credentials);
    }
}

