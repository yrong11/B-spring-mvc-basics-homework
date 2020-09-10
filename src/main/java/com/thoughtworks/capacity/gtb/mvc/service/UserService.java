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

}
