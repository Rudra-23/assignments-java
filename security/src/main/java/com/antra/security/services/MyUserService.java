package com.antra.security.services;

import com.antra.security.models.MyUser;
import com.antra.security.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MyUser register(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.myUserRepository.save(user);
        return user;
    }
}
