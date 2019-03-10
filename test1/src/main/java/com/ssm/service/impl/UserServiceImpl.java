package com.ssm.service.impl;

import com.ssm.entity.User;
import com.ssm.dao.UserDao;
import com.ssm.service.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
 * @author 13979
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User findUserById(int id) { return userDao.findUserById(id); }

    @Override
    public User findUserByUsername(String username) { return userDao.findUserByUsername(username); }

    @Override
    public int updateStates(String sid, String content) { return userDao.updateSingleUserState(sid, content); }

    @Override
    public int mapProject(@Param("pid") String pid, @Param("sid") String sid, @Param("cancel") Boolean cancel) {
        if(cancel) {
            return userDao.mapProject("", sid);
        } else {
            return userDao.mapProject(pid, sid);
        }
    }

    @Override
    public int addUser(User user) { return userDao.addUser(user); }

    @Override
    public int deleteUser(int id) { return userDao.deleteUser(id); }

    @Override
    public User loginCheck(String username, String password) {
        User user = userDao.findUserByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

}
