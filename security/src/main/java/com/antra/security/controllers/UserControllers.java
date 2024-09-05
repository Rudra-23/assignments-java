package com.antra.security.controllers;

import com.antra.security.models.MyUser;
import com.antra.security.services.JWTService;
import com.antra.security.services.MyUserDetailsService;
import com.antra.security.services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllers {

    @Autowired
    private MyUserService myUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_User')")
    public String getHome() {
        return "Main page";
    }

    @GetMapping("/user/")
    public String getUser() {
        return "User page";
    }

    @GetMapping("/admin/")
    public String getAdmin() {
        return "Admin page";
    }

    @PostMapping("/register/user")
    public MyUser postUser(@RequestBody MyUser user) {
        return myUserService.register(user);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginForm.username(), loginForm.password()
        ));

        if(authentication.isAuthenticated()) {
            return jwtService.generateToken(myUserDetailsService.loadUserByUsername(loginForm.username()));
        }
        else {
            throw new UsernameNotFoundException(loginForm.username() + "does not exist.");
        }
    }

}
