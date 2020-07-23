package com.yuner.Service;

import com.yuner.Entity.User;

public interface UserService {
    User checkUser(String username, String password);
}
