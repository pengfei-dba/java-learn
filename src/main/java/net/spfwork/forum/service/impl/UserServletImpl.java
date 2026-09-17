package net.spfwork.forum.service.impl;

import net.spfwork.forum.dao.UserDao;
import net.spfwork.forum.domain.User;
import net.spfwork.forum.service.UserService;
import net.spfwork.forum.util.Md5sumUtils;

import java.time.LocalDateTime;

public class UserServletImpl implements UserService {
    UserDao userDao=new UserDao();

    @Override
    public int register(User user) {
        user.setRole(1);
        user.setCreateTime(LocalDateTime.now());
        user.setImg("default.jpg");
        user.setPwd(Md5sumUtils.msgToMD5(user.getPwd()));
        try{
            return userDao.save(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public User login(String phone, String pwd) {
        String pwdMd5=Md5sumUtils.msgToMD5(pwd);
        User user=null;
        try{
            user=userDao.findByPhoneAndPwd(phone,pwdMd5);
        }catch (Exception e){
            e.printStackTrace();
        }
        return user;
    }
}
