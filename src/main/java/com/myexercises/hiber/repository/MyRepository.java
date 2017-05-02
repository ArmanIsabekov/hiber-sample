/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.repository;

import com.myexercises.hiber.entity.Data;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Acer
 */
public interface MyRepository extends CrudRepository<Data, UUID> {
    
}
