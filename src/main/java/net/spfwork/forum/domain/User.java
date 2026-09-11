package net.spfwork.forum.domain;

import java.time.LocalDateTime;

/**
 * 用户类，用于表示系统中的用户信息
 * 该类是一个基本的用户实体类，可以扩展用户相关的属性和方法
 * CREATE TABLE `user` (
 *   `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
 *   `phone` varchar(32) DEFAULT NULL,
 *   `pwd` varchar(128) DEFAULT NULL,
 *   `sex` int(2) DEFAULT NULL COMMENT '0是女，1是男，2未知',
 *   `img` varchar(128) DEFAULT NULL,
 *   `create_time` datetime DEFAULT NULL,
 *   `role` int(11) DEFAULT NULL COMMENT '1是普通用户，2是管理员',
 *   `username` varchar(128) DEFAULT NULL,
 *   PRIMARY KEY (`id`),
 *   UNIQUE KEY `phone` (`phone`)
 * ) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4
 */

public class User {
    private int id;
    private String phone;
    private String pwd;
    private int sex;
    private String img;
    private LocalDateTime createTime;
    private int role;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", sex=" + sex +
                ", img='" + img + '\'' +
                ", createTime=" + createTime +
                ", role=" + role +
                ", username='" + username + '\'' +
                '}';
    }
}
