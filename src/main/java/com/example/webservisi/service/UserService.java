package com.example.webservisi.service;

import com.example.webservisi.domain.User;

import java.util.Set;

public interface UserService {
    public Set<User> getUsers();

    public void register(String username, String firstPassword, String secondPassword) throws Exception;

    public String login (String username, String password);

    public void unregister(String username, String firstPassword, String secondPassword) throws Exception;
}
