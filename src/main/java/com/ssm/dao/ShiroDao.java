package com.ssm.dao;

import com.ssm.entity.User;
import com.ssm.entity.Permission;

import java.util.List;

public interface ShiroDao {

    public User getUserByUserName(String username);

    public List<Integer> getUserRoleByUserId(int id);

    public List<Permission> getPermissionsByRoleId(int roleId);
}
