package com.ssm.dao;

import com.ssm.entity.User;
import com.ssm.entity.Permission;

import java.util.List;

/**
 *
 * @author 13979
 */
public interface ShiroDao {

    User getUserByUserName(String username);

    List<Integer> getUserRoleByUserId(int id);

    List<Permission> getPermissionsByRoleId(int roleId);
}
