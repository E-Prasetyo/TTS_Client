/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.services;

import com.ksm.models.Employee;
import com.ksm.models.LoginRequest;
import com.ksm.models.LoginResponse;
import com.ksm.models.Person;
import com.ksm.models.Register;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author WahyuKu
 */
@Service
public class LoginService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${base.url}")
    private String URL;
    
    public boolean login(LoginRequest request) {
        try {
            ResponseEntity<LoginResponse> response = 
                    restTemplate.postForEntity(URL+"/login", request, LoginResponse.class); 
            return true;
        } catch (HttpStatusCodeException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) {
                return false;
            }
        }
        return false;
    }
    
    public boolean register(Register register) {
         try {
            ResponseEntity<Boolean> response = 
                    restTemplate.postForEntity(URL+"/register", register, Boolean.TYPE);
            return true;
        } catch (HttpStatusCodeException e) {
            return false;
        }
    }
    
    public void setSession(LoginRequest request, LoginResponse response) {
        List<String> roles = new ArrayList<>();
        for (String t : response.getRoles()){
            roles.add(t);
        }
        UsernamePasswordAuthenticationToken token = 
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword(), 
                        authorities(roles));
        SecurityContextHolder.getContext().setAuthentication(token);
    }
    
    public List<GrantedAuthority> authorities(List<String> roles) {
        List<GrantedAuthority> auths = new ArrayList<>();
        
        for (String role : roles) {
            auths.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
        }
        
        return auths;
    }
    
    public List<Employee> getAllEmployee() {
        ResponseEntity<List<Employee>> response = restTemplate
                .exchange(URL+"/emp", HttpMethod.GET, null, 
                new ParameterizedTypeReference<List<Employee>>(){});
        
        return response.getBody();
    }
}
