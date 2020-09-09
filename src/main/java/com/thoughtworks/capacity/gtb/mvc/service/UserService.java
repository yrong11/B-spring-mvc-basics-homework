package com.thoughtworks.capacity.gtb.mvc.service;

import com.thoughtworks.capacity.gtb.mvc.domain.User;
import com.thoughtworks.capacity.gtb.mvc.exception.UserExistException;
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
}
