/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.controller;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json; charset=UTF-8"))
                .andExpect(jsonPath("$.description").value("my first description"));
        
    }
}
