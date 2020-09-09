package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginException;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user) throws UserExistException {
        userService.register(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam("username") String username, @RequestParam("password") String pwd) throws UserLoginException {
        User user = userService.login(username, pwd);
        return ResponseEntity.ok(user);

    }

}
