/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.models;

import lombok.Data;

/**
 *
 * @author user
 */
@Data
public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String gender;
    private String address;
    private String phone;
    private boolean isDeleted;

    public Employee() {
    }
    
    
}
