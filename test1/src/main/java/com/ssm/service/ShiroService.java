package com.ssm.service;

import com.ssm.entity.Permission;
import com.ssm.entity.User;


import java.util.List;

public interface ShiroService {

    public User getUserByUserName(String username);

    public List<Permission> getPermissionsByUser(User user);
}
