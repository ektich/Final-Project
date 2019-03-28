package com.ssm.dao;

import com.ssm.entity.User;
import org.apache.ibatis.annotations.Param;


/**
 * @author 13979
 */

public interface UserDao {

    /**
     * find user by user id
     * @param id user id
     * @return user object
     */
    User findUserById(int id);

    /**
     * find user by username
     * @param username username
     * @return user object
     */
    User findUserByUsername(String username);

    /**
     * add user
     * @param user user object
     * @return 1 success 0 fail
     */
    int addUser(User user);

    /**
     * delete user by user id
     * @param id id
     * @return 1 success 0 fail
     */
    int deleteUser(int id);

    /**
     * update lectures' posted projects save project id
     * @param sid username
     * @param content project id
     * @return 1 success 0 fail
     */
    int updateSingleUserState(@Param("sid") String sid, @Param("content") String content);

    /**
     * update student mapper
     * @param pid project id
     * @param sid student id
     * @return 1 success 0 fail
     */
    int mapProject(@Param("pid") String pid, @Param("sid") String sid);
}
