package com.demo.scrum.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.scrum.constant.Constants;
import com.demo.scrum.domain.User;
import com.demo.scrum.exception.UnauthorizedException;
import com.demo.scrum.service.UserService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    private UserService userService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, UserService userService) {
        super(authenticationManager);
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String queryParmaeterToken = req.getParameter("token");
        String header = req.getHeader("Authorization");

        if ((header == null || !header.startsWith("Bearer ")) && queryParmaeterToken == null) {
            chain.doFilter(req, res);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(req);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(req, res);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
        String token = req.getHeader("Authorization");

        if (token == null) {
            token = req.getParameter("token");
        }

        if (token != null) {
            String userID = varifyJwtToken(token).getBody().getSubject();

            if (userID != null) {
                Optional<User> currentUser = userService.get(Integer.parseInt(userID));

                if (currentUser.isPresent()) {
                    User realUser = currentUser.get();
                    realUser.setPassword("");
                    return new UsernamePasswordAuthenticationToken(realUser, null, new ArrayList<>());
                }
            }
        }

        throw new UnauthorizedException();
    }

    private Jws<Claims> varifyJwtToken(String token) {
        try {
            return Jwts.parser().setSigningKey(Constants.key).parseClaimsJws(token.replace("Bearer ", ""));
        } catch (JwtException e) {
            throw new UnauthorizedException();
        }
    }
}