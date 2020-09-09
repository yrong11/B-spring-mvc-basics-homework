package com.thoughtworks.capacity.gtb.mvc.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Data
@Valid
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotEmpty(message = "用户名不能为空") @Size(max = 10, min = 3, message = "用户名不合法")
    @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$", message = "用户名不合法")
    private String username;

    @NotEmpty(message = "密码是不为空") @Size(min = 5, max = 12, message = "密码不合法")
    private String password;

    @Email(message = "邮箱地址不合法")
    private String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
}