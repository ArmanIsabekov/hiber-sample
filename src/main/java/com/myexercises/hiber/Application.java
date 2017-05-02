/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.myexercises.hiber;

import com.myexercises.hiber.config.JpaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author Acer
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(new Object[] {Application.class, JpaConfig.class}, args);
    }
}
