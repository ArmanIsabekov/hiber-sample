/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.repository;

import com.myexercises.hiber.entity.Data;
import java.util.Collections;
import java.util.UUID;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Acer
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyRepositoryImplTest {
    @Autowired
    MyRepository myRepository;
    
    public MyRepositoryImplTest() {
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
    public void testSave_GenericType() {
        Data data = new Data("test description");
        myRepository.save(data);
        Data found = myRepository.findOne(data.getId());
        assertEquals(data, found);
        myRepository.delete(data);
    }

    @Test
    public void testSave_Iterable() {
    }

    @Test
    public void testFindOne() {
        UUID uuid = UUID.fromString("61e45edf-2cd5-4572-9290-d92f9539a4fa");
        Data data = myRepository.findOne(uuid);
        assertEquals("my first description", data.getDescription());
    }

    @Test
    public void testExists() {
        UUID uuid = UUID.fromString("61e45edf-2cd5-4572-9290-d92f9539a4fa");
        assertTrue(myRepository.exists(uuid));
    }

    @Test
    public void testFindAll_0args() {
        Iterable<Data> found = myRepository.findAll();
        assertNotNull(found);
        assertNotNull(found.iterator());
        assertTrue(found.iterator().hasNext());
        UUID uuid = UUID.fromString("61e45edf-2cd5-4572-9290-d92f9539a4fa");
        Data data = found.iterator().next();
        assertEquals(uuid, data.getId());
        assertEquals("my first description", data.getDescription());
    }

    @Test
    public void testFindAll_Iterable() {
        UUID uuid = UUID.fromString("61e45edf-2cd5-4572-9290-d92f9539a4fa");
        Iterable<Data> found = myRepository.findAll(Collections.singleton(uuid));
        assertNotNull(found);
        assertNotNull(found.iterator());
        assertTrue(found.iterator().hasNext());
        Data data = found.iterator().next();
        assertEquals(uuid, data.getId());
        assertEquals("my first description", data.getDescription());
    }

    @Test
    public void testCount() {
        long count = myRepository.count();
        assertEquals(4, count);
    }

    @Test
    public void testDelete_UUID() {
    }

    @Test
    public void testDelete_Data() {
    }

    @Test
    public void testDelete_Iterable() {
    }

    @Test
    public void testDeleteAll() {
    }
    
}
