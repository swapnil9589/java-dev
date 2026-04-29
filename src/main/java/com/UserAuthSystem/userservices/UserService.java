package com.UserAuthSystem.userservices;

import com.UserAuthSystem.userentity.User;
import com.UserAuthSystem.userrepository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public String saveEntry(User user) {
        if (user != null &&
                !user.getUsername().isEmpty() &&
                !user.getPassword().isEmpty()) {
            userRepo.save(user);
            return "user created successfully";
        }
        return new NullPointerException("please provide detail").getMessage();
    }

    public Object updateUser(String username, User user) {
        User dbuser = userRepo.findByUsername(username);
        if (dbuser == null) {
            return new NullPointerException("please check given username").getMessage();
        }
        if (user != null)
            return new NullPointerException("please provide details");
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            dbuser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            dbuser.setPassword(user.getPassword());
        }
        userRepo.save(dbuser);
        return "";
    }

    public Object finduserByname(String username) {
        User dbuser = userRepo.findByUsername(username);
        if (dbuser != null) return dbuser;
        return new Exception("user not found").getMessage();
    }


}