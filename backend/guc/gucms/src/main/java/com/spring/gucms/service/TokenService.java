package com.spring.gucms.service;

import com.auth0.jwt.JWT;

import com.spring.gucms.dto.JwtLogin;
import com.spring.gucms.dto.JwtResponse;
import com.spring.gucms.dto.UserPrincipal;
import com.spring.gucms.dto.JwtProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Service
public class TokenService {
    AuthenticationManager authenticationManager;
    @Autowired
    public TokenService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public String generateToken(Authentication authResult) {

        // Grab principal
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();
        System.out.println(principal.getUsername());

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtProperties.EXPIRATION_TIME))
                .sign(HMAC512(JwtProperties.SECRET.getBytes()));
        return token;
    }
    public JwtResponse login(JwtLogin jwtLogin) {
        System.out.println("Hello World");
        System.out.println(jwtLogin.getUserName());
        System.out.println(jwtLogin.getPassword());
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtLogin.getUserName(),
                jwtLogin.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = generateToken(authenticate);

        return new JwtResponse(jwtLogin.getUserName(),token);
    }
}
