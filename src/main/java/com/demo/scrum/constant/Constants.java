package com.demo.scrum.constant;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class Constants {
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final Integer exp = 50 * 60 * 1000;
}