package com.ludayo.dao;

import com.ludayo.domain.Users;

import java.util.List;

public interface UserDao {
    /**
     * 查询所有用户信息
     * @return
     */
    List<Users> findAll();

    /**
     * 注册用户
     * @param users
     */
    void registerUser(Users users);
}
