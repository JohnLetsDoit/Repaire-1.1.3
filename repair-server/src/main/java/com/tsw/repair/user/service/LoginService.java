package com.tsw.repair.user.service;


import com.tsw.repair.user.model.User;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    public User login(User user);
}
