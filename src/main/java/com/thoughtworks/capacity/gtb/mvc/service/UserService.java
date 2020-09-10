package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.ErrorMsg;
import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;
import com.thoughtworks.capacity.gtb.mvc.exception.UserLoginException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    public static List<User> users = new ArrayList<>();

    public void register(User user) throws UserExistException {
        if (users.contains(user)) {
            throw new UserExistException();
        }
        users.add(user);
    }

    public User login(String username, String pwd) throws UserLoginException {
        validateLogin(username, pwd);
        if (isExistUser(username)){
            User user = getUserByName(username);
            if (!checkPwd(user, pwd))
                throw new UserLoginException(ErrorMsg.USER_NAME_OR_PWD_ERROR);
            return user;
        }else{
            throw new UserLoginException(ErrorMsg.USER_NOT_EXIST);
        }
    }

    public boolean isExistUser(String username){
        return users.stream().filter(item -> item.getUsername().equals(username)).findFirst().isPresent();
    }

    public boolean checkPwd(User user, String pwd) {
        return user.getPassword().equals(pwd);
    }

    public User getUserByName(String name) {
        return users.stream().filter(item -> item.getUsername().equals(name)).findFirst().get();

    }
    public void validateLogin(String username, String pwd) throws UserLoginException {
        String regex = "^[0-9a-zA-Z_]{1,}$";
        if (username.length() > 10 || username.length() < 3 || !username.matches(regex))
            throw new UserLoginException(ErrorMsg.USER_NAME_NOT_VALID);
        if (pwd.length() > 12 || pwd.length() < 5)
            throw new UserLoginException(ErrorMsg.PASSWORD_NOT_VALID);
    }
}
