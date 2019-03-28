package com.ssm.service.impl;

import com.ssm.dao.ShiroDao;
import com.ssm.entity.User;
import com.ssm.entity.Permission;
import com.ssm.service.ShiroService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 13979
 */

@Service("shiroService")
public class ShiroServiceImpl implements ShiroService {

    @Resource
    private ShiroDao shiroDao;

    @Override
    public User getUserByUserName(String username) {
        //根据账号获取账号密码
        try {
            System.out.println(shiroDao);
            return shiroDao.getUserByUserName(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
        return perArray;
    }
}
