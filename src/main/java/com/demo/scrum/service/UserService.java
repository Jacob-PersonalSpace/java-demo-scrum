package com.demo.scrum.service;

import java.util.Date;

import com.demo.scrum.constant.ConstantKey;
import com.demo.scrum.domain.User;
import com.demo.scrum.repository.UserRepository;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public User findByNameAndPassword(User user) {
        return userRepository.findOne(user.getName(), user.getPassword());
    }

    public User findByName(User user) {
        return userRepository.findByName(user.getName());
    }

    public String generateToken(User user) {
        User userVo = findByName(user);

        if (userVo != null) {
            String token = Jwts.builder().setSubject(userVo.getId().toString())
                    .setExpiration(new Date(System.currentTimeMillis() + 30 * 1000))
                    .signWith(ConstantKey.key).compact();

            return token;
        } else {
            // throw not found username
            return "hahah";
        }
    }
}