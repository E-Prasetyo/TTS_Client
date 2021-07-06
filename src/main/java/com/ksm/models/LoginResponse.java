/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.models;

import java.util.List;
import java.util.Set;
import lombok.Data;

/**
 *
 * @author WahyuKu
 */
@Data
public class LoginResponse {
    
    private String email;
    private Set<String> roles;

    public LoginResponse() {
    }
}
