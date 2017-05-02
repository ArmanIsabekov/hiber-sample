/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber.entity;

import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Acer
 */
@Entity(name="yourapp_data")
@SuppressWarnings("PersistenceUnitPresent")
public class Data implements DomainObject {
    @Id
    @GeneratedValue
    @Column(name="data_id")
    private UUID id;
    @Column(name="data_description")
    private String description;

    public Data() {
    }

    public Data(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public Data(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}