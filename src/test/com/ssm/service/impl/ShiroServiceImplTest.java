package com.ssm.service.impl;

import com.ssm.entity.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class ShiroServiceImplTest {

    @Test
    public void getUserByUserName() {
        User user = new User();
        user.setUsername("18000000");
        user.setPassword("123");
        
    }
}