package com.demo.scrum.service;

import java.util.Date;
import java.util.Optional;

import com.demo.scrum.constant.Constants;
import com.demo.scrum.domain.User;
import com.demo.scrum.exception.PasswordNotMatchedException;
import com.demo.scrum.exception.UserNotFoundException;
import com.demo.scrum.repository.UserRepository;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> get(Integer userID) {
        return userRepository.findById(userID);
    }

    public User create(User user) {
        String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);

        return userRepository.save(user);
    }

    public String generateToken(String name, String password) {
        User currentUser = userRepository.findOne(name);

        if (currentUser == null) {
            throw new UserNotFoundException("User " + name + " is not existend.");
        }

        if (!bCryptPasswordEncoder.matches(password, currentUser.getPassword())) {
            throw new PasswordNotMatchedException("Password is not matched.");
        }

        return generateJwt(currentUser.getId().toString());
    }

    public String refreshToken() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return generateJwt(currentUser.getId().toString());
    }

    private String generateJwt(String userID) {
        return Jwts.builder().setSubject(userID).setExpiration(new Date(System.currentTimeMillis() + Constants.exp))
                .signWith(Constants.key).compact();
    }
}