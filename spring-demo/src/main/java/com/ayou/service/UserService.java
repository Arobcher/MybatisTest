package com.ayou.service;

import com.ayou.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void deleteUser(User user) {
        System.out.println("成功删除User " + user.getName());
    }

    public void deleteUserException(User user) {
        throw new NullPointerException("not found user");
    }
}
