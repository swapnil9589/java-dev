package com.UserAuthSystem.usercontroller;

import com.UserAuthSystem.userentity.User;
import com.UserAuthSystem.userrepository.UserRepo;
import com.UserAuthSystem.userservices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/User")
public class UserControllerClass {
    @Autowired
    private UserService userservice;
    @Autowired
    private UserRepo userRepo;

    @PostMapping
    public ResponseEntity<String> saveUser(@RequestBody User user) {

        System.out.println("user is " + user);
        String createdUser = userservice.saveEntry(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/{username}")
    public ResponseEntity<Object> changeUserNameOrPassword(@PathVariable String username, @RequestBody User user) {
        Object changeduser = userservice.updateUser(username, user);
        return new ResponseEntity<>(changeduser, HttpStatus.CREATED);
    }

    @DeleteMapping
    public boolean deleteall() {
        List<User> user = userRepo.findAll();
                userRepo.deleteAll();
        return true;
    }

    @GetMapping("username/{username}")
    public Object findUser(@PathVariable String username) {
        return userservice.finduserByname(username);
    }

}
