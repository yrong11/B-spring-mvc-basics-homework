package com.thoughtworks.capacity.gtb.mvc.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.Objects;

@Data
@Valid
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long id = System.currentTimeMillis();

    @NotEmpty(message = ErrorMsg.USER_NAME_IS_NULL) @Size(max = 10, min = 3, message = ErrorMsg.USER_NAME_NOT_VALID)
    @Pattern(regexp = "^[0-9a-zA-Z_]{1,}$", message = ErrorMsg.USER_NAME_NOT_VALID)
    private String username;

    @NotEmpty(message = ErrorMsg.PASSWORD_IS_NULL) @Size(min = 5, max = 12, message = ErrorMsg.PASSWORD_NOT_VALID)
    private String password;

    @Email(message = ErrorMsg.EMAIL_NOT_VALID)
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
