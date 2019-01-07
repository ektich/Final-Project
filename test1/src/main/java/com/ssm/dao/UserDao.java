package com.ssm.dao;

import com.ssm.entity.User;
import org.apache.ibatis.annotations.Param;


/**
 * @author 13979
 */

public interface UserDao {

    public User findUserById(int id);

    public User findUserByUsername(String username);

    public int addUser(User user);

    public int deleteUser(int id);

}
