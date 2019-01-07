package com.ssm.service;

import com.ssm.entity.User;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author 13979
 */
public interface UserService {


    public User findUserById(int id);

    public User findUserByUsername(String username);

    public int addUser(User user);

    public int deleteUser(int id);

    public User loginCheck(String username, String password);

}
