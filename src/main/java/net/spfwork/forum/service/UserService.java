package net.spfwork.forum.service;

import net.spfwork.forum.domain.User;

public interface UserService {
    int register(User user);

    User login(String phone,String pwd);


}
