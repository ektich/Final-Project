package com.ssm.controller;

import com.ssm.service.ShiroService;
import com.ssm.shiro.MyShiroRealm;
import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/application-context.xml",
        "classpath:spring/spring-mvc.xml", "classpath:spring/spring-mybatis.xml"})
@Transactional
@Rollback
@WebAppConfiguration

public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ShiroService shiroService;

    @Before()
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testQueryStates() throws Exception {

        String url = "/states";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .param("id", "12000000")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testLogin() throws Exception {
        //no shiroDao
        securityManager();
        accountService();
        String url = "/login.do";

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "18000000")
                .param("password", "123")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();
    }

    public void securityManager() {
        String resource = "spring/shiro-context.xml";
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);
        org.apache.shiro.mgt.SecurityManager securityManager =
                (org.apache.shiro.mgt.SecurityManager)appCtx.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);

    }

    public void accountService() {
        String resource = "spring/shiro-context.xml";
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);
        com.ssm.service.impl.ShiroServiceImpl shiroService =
                (com.ssm.service.impl.ShiroServiceImpl)appCtx.getBean("accountService");
        MyShiroRealm myShiroRealm = new MyShiroRealm();
        myShiroRealm.setShiroService(shiroService);
    }

}