package com.ssm.controller;

import com.hazelcast.console.Echo;
import com.ssm.entity.Project;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/application-context.xml",
        "classpath:spring/spring-mvc.xml", "classpath:spring/spring-mybatis.xml"})
@Transactional
@Rollback
@WebAppConfiguration
public class ProjectControllerTest {

    //private static final Logger logger = LoggerFactory.getLogger(HelloControllerTest.class);

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSingleStatus() throws Exception {

        String url = "/project/single";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("id", "1").param("name","false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testGetAllProjects() throws Exception {

        String url = "/project/all/1";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testTopList() throws Exception {

        String url = "/project/topList";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testDisposeProject() throws Exception{

        String url = "/project/dispose";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("username", "Lecture1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testqueryLevel() throws Exception {

        String url = "/project/level";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("code", "0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testInterestedProject() throws Exception {

        String url = "/project/interested";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("pid", "11")
                .param("sid", "18000000")
                .param("tendency", "4")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testDesignateStudent() throws Exception {

        String url = "/project/designate";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("pid", "11")
                .param("sid", "18000000")
                .param("isCancel", "false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testCancelStudent() throws Exception {

        String url = "/project/designate";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .param("pid", "4")
                .param("sid", "18000000")
                .param("isCancel", "true")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testInsertProject() throws Exception {

        String url = "/project/insert";
        String data = "{\"title\":\"test1\",\"supervisor\":\"test1\",\"reference\":\"test1\",\"supervisor\":\"test1\",\"description\":\"test1\",\"language\":\"test1\"}";
        JSONObject json = new JSONObject(data);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON).content(data))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testDeleteProject() throws Exception {

        String url = "/project/delete";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .param("code", "62"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

}