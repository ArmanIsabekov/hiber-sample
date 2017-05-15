/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.controller;

import com.myexercises.hiber.entity.Data;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author Acer
 */
@RunWith(SpringRunner.class)
@SpringBootTest
//@AutoConfigureMockMvc
public class MvcControllerTest {
    @Autowired
    WebApplicationContext wac;
    //@Autowired
    MockMvc mockMvc;
    
    public MvcControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testFindOne() throws Exception {
        mockMvc.perform(get("/data/61e45edf-2cd5-4572-9290-d92f9539a4fa"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.description").value("my first description"));
        
    }
    
    @Test
    public void testFindOneWithClient() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "/data/dc3732e5-66c9-4f82-a847-28e67d01ed11";
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"id\":\"dc3732e5-66c9-4f82-a847-28e67d01ed11\",\"description\":\"my second description\"}", MediaType.APPLICATION_JSON_UTF8));
        Data data = restTemplate.getForObject("/data/{id}", Data.class, "dc3732e5-66c9-4f82-a847-28e67d01ed11");
        mockServer.verify();
    }
    
    @Test
    public void testFindMany() throws Exception {
        RestTemplate restTemplate = new RestTemplate();
        String url = "/find/";
        MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);
        
        mockServer.expect(requestTo(url))
                .andExpect(method(HttpMethod.POST))
                .andExpect(MockRestRequestMatchers.jsonPath("$[0]").value("61e45edf-2cd5-4572-9290-d92f9539a4fa"))
                .andExpect(MockRestRequestMatchers.jsonPath("$[1]").value("dc3732e5-66c9-4f82-a847-28e67d01ed11"))
                .andRespond(withSuccess("[{\"id\":\"61e45edf-2cd5-4572-9290-d92f9539a4fa\",\"description\":\"my first description\"},{\"id\":\"dc3732e5-66c9-4f82-a847-28e67d01ed11\",\"description\":\"my second description\"}]", MediaType.APPLICATION_JSON_UTF8));
        List list = restTemplate.postForObject(url, new String[]{"61e45edf-2cd5-4572-9290-d92f9539a4fa","dc3732e5-66c9-4f82-a847-28e67d01ed11"}, List.class);
        mockServer.verify();
    }
}
