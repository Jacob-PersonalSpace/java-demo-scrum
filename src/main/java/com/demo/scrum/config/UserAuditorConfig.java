package com.demo.scrum.config;

import java.util.Optional;

import com.demo.scrum.domain.User;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class UserAuditorConfig implements AuditorAware<User> {

    @Override
    public Optional<User> getCurrentAuditor() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.of(currentUser);
    }

}