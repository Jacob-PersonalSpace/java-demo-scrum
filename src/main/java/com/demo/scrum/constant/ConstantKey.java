package com.demo.scrum.constant;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class ConstantKey {
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
}