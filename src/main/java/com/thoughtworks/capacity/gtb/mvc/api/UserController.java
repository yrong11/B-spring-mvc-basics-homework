package com.thoughtworks.capacity.gtb.mvc.api;

import com.thoughtworks.capacity.gtb.mvc.domain.ErrorMsg;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginException;
import com.thoughtworks.capacity.gtb.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@RestController
@Validated
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity register(@Valid @RequestBody User user) throws UserExistException {
        userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestParam("username") @Valid
                    @NotEmpty(message = ErrorMsg.USER_NAME_IS_NULL)
                    @Size(max = 10, min = 3, message = ErrorMsg.USER_NAME_NOT_VALID)
                    @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$", message = ErrorMsg.USER_NAME_NOT_VALID) String username, @RequestParam("password")
                    @NotEmpty(message = ErrorMsg.PASSWORD_IS_NULL) @Valid
                    @Size(min = 5, max = 12, message = ErrorMsg.PASSWORD_NOT_VALID) String pwd) throws UserLoginException {
        User user = userService.login(username, pwd);
        return ResponseEntity.ok(user);

    }

}
