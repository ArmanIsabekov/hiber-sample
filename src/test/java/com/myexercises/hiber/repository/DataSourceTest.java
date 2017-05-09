/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Acer
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("hikari")
public class DataSourceTest {
    private static Logger logger = LoggerFactory.getLogger(DataSourceTest.class);
    @Autowired
    DataSource dataSource;
    
    public DataSourceTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testGetCloseConnection() throws Exception {
        List<String> descriptions = new ArrayList<>();
        try (
                Connection con = dataSource.getConnection(); 
                PreparedStatement ps = con.prepareStatement(
                        "select data_id, data_description from yourapp_data",
                        ResultSet.TYPE_FORWARD_ONLY,
                        ResultSet.CONCUR_READ_ONLY
                );
                ResultSet rs = ps.executeQuery()
                ) {
            while (rs.next()) {
                String description = rs.getString(2);
                descriptions.add(description);
                //con.close();
            }
        }
        logger.info(descriptions.toString());
    }
}
