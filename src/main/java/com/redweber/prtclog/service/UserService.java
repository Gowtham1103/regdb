package com.redweber.prtclog.service;

import com.redweber.prtclog.entity.User;
import com.redweber.prtclog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public static boolean isValidPassword(String password) {

        if (password.length() < 8) {
            return false;
        }

        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        if (!password.matches(".*[!@#$%^&*()\\-_+=<>?/{}\\[\\]\\|].*")) {
            return false;
        }
        return true;
    }
    public ResponseEntity<String> createUser(User credentials) {

        if ((userRepository.existsByUsername(credentials.getUsername()))){
            return ResponseEntity.status(HttpStatus.FOUND).body("username already exists !");
        }
        if (!isValidPassword(credentials.getPassword())){
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Your password must be at least 8 characters long and contain at least one uppercase letter and one special character.");
        }
        userRepository.save(credentials);
        return ResponseEntity.status(HttpStatus.CREATED).body("user account created.");
    }

    public ResponseEntity<String> checkUser(User credentials) {
        User user = userRepository.findByUsername(credentials.getUsername());
        System.out.println(user);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Username");
        }
        if (!user.getPassword().equals(credentials.getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Password");
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Sign in Successful !");
    }
}
