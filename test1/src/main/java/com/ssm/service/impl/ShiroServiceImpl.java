package com.ssm.service.impl;

import com.ssm.dao.ShiroDao;
import com.ssm.entity.User;
import com.ssm.entity.Permission;
import com.ssm.service.ShiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13979
 */

@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

    @Autowired
    private ShiroDao shiroDao;

    @Override
    public User getUserByUserName(String username) {
        //根据账号获取账号密码
        return shiroDao.getUserByUserName(username);
    }

    @Override
    public List<Permission> getPermissionsByUser(User user) {
        //获取到用户角色userRole
        List<Integer> roleId = shiroDao.getUserRoleByUserId(user.getId());
        List<Permission> perArray = new ArrayList<>();
        if (roleId != null && roleId.size() != 0) {
            //根据roleid获取peimission
            for (Integer i : roleId) {
                perArray.addAll(shiroDao.getPermissionsByRoleId(i));
            }
        }
        System.out.println(perArray);
        return perArray;
    }
}
