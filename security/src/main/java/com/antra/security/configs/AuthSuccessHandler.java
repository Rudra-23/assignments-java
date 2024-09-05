package com.antra.security.configs;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        List<String> roles = authentication.getAuthorities().stream().map(Object::toString).toList();

        System.out.println(roles);
        if(roles.contains("ROLE_Admin")) {
            setDefaultTargetUrl("/admin/");
        }
        else{
            setDefaultTargetUrl("/user/");
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
