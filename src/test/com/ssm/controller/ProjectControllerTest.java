package com.ssm.controller;

import org.json.JSONArray;
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
    public void testGetSingleProject() throws Exception {
        String url = "/project/single";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "1").param("name","false")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testGetAllProjects() throws Exception {
    //1
        String url = "/project/all/1";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testGetTopList() throws Exception {
    //1
        String url = "/project/topList";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testDisposeProject() throws Exception{
    //1
        String url = "/project/dispose";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("username", "Lecture1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testQueryLevel() throws Exception {
    //1
        String url = "/project/level";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_JSON)
                .param("code", "0")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testInterestedProject() throws Exception {
    //1
        String url = "/project/interested";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
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
    //1
        String url = "/project/designate";
        String data = "[{\"pid\":\"1\",\"sid\":\"18000000\",\"isCancel\":\"false\"},{\"pid\":\"1\",\"sid\":\"12000000\",\"isCancel\":\"false\"}]";
        //{"list":[{"pid":"1","sid":"18000000","isCancel":"false"},{"pid":"1","sid":"12000000","isCancel":"false"}]}
        JSONArray jsonArray = new JSONArray(data);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonArray.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testCancelStudent() throws Exception {
    //1
        //note: should designate candidate first
        String url = "/project/designate";
        String data = "[{\"pid\":\"1\",\"sid\":\"18000000\",\"isCancel\":\"true\"}]";
        JSONArray jsonArray = new JSONArray(data);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonArray.toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testInsertProject() throws Exception {
    //1
        String url = "/project/insert";
        String data = "{\"title\":\"test1\",\"supervisor\":\"test1\",\"reference\":\"test1\",\"supervisor\":\"test1\",\"description\":\"test1\",\"language\":\"test1\"}";
        JSONObject json = new JSONObject(data);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(data)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

    @Test
    public void testDeleteProject() throws Exception {
    //1
        String url = "/project/delete";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(url)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("code", "62")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(print())
                .andReturn();

    }

}