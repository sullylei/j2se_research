package com.sully.dynamicproxy;

/**
 * Creator: lei.s
 * Create Date: 2017年12月29日-18:23
 *
 * @Description:
 */
public class UserServiceImpl implements UserService {
    public void addUser() {
        System.out.println("add user");
    }
    public void removeUser() {
        System.out.println("remove user");
    }
    public void searchUser() {
        System.out.println("search user");
    }
}
