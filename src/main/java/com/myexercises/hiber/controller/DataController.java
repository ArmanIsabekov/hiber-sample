/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.controller;

import com.myexercises.hiber.entity.Data;
import com.myexercises.hiber.repository.MyRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Acer
 */
@RestController
@RequestMapping("data")
public class DataController {
    @Autowired
    private MyRepository myRepository;
    
    @GetMapping("/{id}")
    public Data findOne(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return myRepository.findOne(uuid);
    }
    
    @GetMapping("/all")
    public Iterable<Data> findAll() {
        return myRepository.findAll();
    }
    
    @PostMapping(value="save", consumes = {"application/json"}, produces = {"application/json"})
    public Data save(@RequestBody Data req) {
        return myRepository.save(req);
    }
    
    @PostMapping(value="/find", consumes={"application/json"})
    public Iterable<Data> findAll(@RequestBody List<String> ids) {
        List<UUID> idList = new ArrayList<>();
        ids.stream().map((id) -> UUID.fromString(id)).forEachOrdered((uuid) -> {
            idList.add(uuid);
        });
        return myRepository.findAll(idList);
    }
    
    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        myRepository.delete(uuid);
    }
}
