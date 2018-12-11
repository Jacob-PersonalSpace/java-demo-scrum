package com.demo.scrum.service;

import java.util.Date;

import com.demo.scrum.constant.Constants;
import com.demo.scrum.domain.User;
import com.demo.scrum.exception.PasswordNotMatchedException;
import com.demo.scrum.exception.UserNotFoundException;
import com.demo.scrum.repository.UserRepository;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User create(User user) {
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userRepository.save(user);
    }

    public String generateToken(User user) {
        User dbUser = userRepository.findOne(user.getName());

        if (dbUser == null) {
            throw new UserNotFoundException("User " + user.getName() + " is not existend.");
        }

        if (!bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new PasswordNotMatchedException("Password is not matched.");
        }

        return Jwts.builder().setSubject(dbUser.getId().toString())
                .setExpiration(new Date(System.currentTimeMillis() + Constants.exp)).signWith(Constants.key).compact();
    }
}