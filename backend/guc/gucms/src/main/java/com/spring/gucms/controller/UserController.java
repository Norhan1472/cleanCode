package com.spring.gucms.controller;

import com.spring.gucms.dto.JwtLogin;
import com.spring.gucms.dto.JwtResponse;
import com.spring.gucms.entity.User;
import com.spring.gucms.service.AuthoritiesService;
import com.spring.gucms.service.TokenService;
import com.spring.gucms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("http://localhost:4200")
public class UserController {
    private TokenService tokenService;

    private PasswordEncoder password;
    private UserService userService;
    private AuthoritiesService authoritiesService;
    @Autowired
    public UserController(TokenService tokenService, PasswordEncoder password, UserService userService, AuthoritiesService authoritiesService) {
        this.tokenService = tokenService;
        this.password = password;
        this.userService = userService;
        this.authoritiesService = authoritiesService;
    }

    @PostMapping("signIn")
    public JwtResponse login(@RequestBody JwtLogin jwtLogin){
        return tokenService.login(jwtLogin);

    }
    @PostMapping("signUp")
    public void signUp(@RequestBody JwtLogin jwtLogin){
        System.out.println("jjjjj");
        System.out.println(jwtLogin.getUserName());
        User userData = new User();
        userData.setEmail(jwtLogin.getUserName());
        userData.setPassword(password.encode(jwtLogin.getPassword()));
        userData.setActive(true);
        userData.getAuthorities().add(authoritiesService.getAllAuthorities().get(0));
        userService.signUp(userData);
    }
}
