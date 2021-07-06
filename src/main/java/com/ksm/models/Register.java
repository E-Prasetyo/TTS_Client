/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.models;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author WahyuKu
 */
@Data
public class Register {
    
    private String id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String date;
    private String gender;
    private String email;
    private String password;

    public Register() {
    }
    
}
