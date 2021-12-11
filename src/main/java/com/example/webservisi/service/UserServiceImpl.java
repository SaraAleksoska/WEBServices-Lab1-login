package com.example.webservisi.service;

import com.example.webservisi.domain.User;
import com.example.webservisi.domain.UserReg;
import com.example.webservisi.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Set<User> getUsers() {
        Set<User> userSet = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(userSet::add);
        return userSet;
    }

    @Override
    public void register(String username, String firstPassword, String secondPassword) throws Exception {
        if(firstPassword.equals(secondPassword)) {
            User user = new User(username, firstPassword);
            userRepository.save(user);
        }
        else {
            throw new Exception("Password and confirm password does not match");
        }
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findUserByUsername(username).get();

        if(user.getPassword().equals(password)) {
            return "Successful log in";
        }
        else {
            return "Incorrect username or password!";
        }
    }

    @Override
    public void unregister(String username, String firstPassword, String secondPassword) throws Exception {
        if(firstPassword.equals(secondPassword)) {
            User user = new User(username, firstPassword);
            userRepository.deleteUserByUsername(username);
        }
        else {
            throw new Exception("Password and confirm password does not match");
        }
    }
}
