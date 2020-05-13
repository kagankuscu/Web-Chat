package com.kagankuscu.webchat.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return new User("javainuse", "$2a$10$wPuWwpGJqarxKfhznPbMW.27yoYNThvv.yNPvftLyHHQ1npUlSdE2", new ArrayList<>());
    }
}
