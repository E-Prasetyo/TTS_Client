/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksm.controllers;

import com.ksm.models.LoginRequest;
import com.ksm.models.Register;
import com.ksm.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author WahyuKu
 */
@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;
    
//    @GetMapping("/login")
//    public String login() {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String result = "";
//        if(auth.getPrincipal().equals("anonymousUser")) {
//            result =  "login";
//        } else {
//            result =  "redirect:/dashboard";
//        }
//        
//        return result;
//    }
//    
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
         model.addAttribute("toPage", "/dashboard");
         model.addAttribute("toPage1", "/employee");
         model.addAttribute("toPage2", "/account");
         model.addAttribute("toPage3", "/role");
        return "dashboard";
    }
    
    @GetMapping("/employee")
    public String employee(Model model) {
         model.addAttribute("employees", loginService.getAllEmployee());
         model.addAttribute("toPage", "/dashboard");
         model.addAttribute("toPage1", "/employee");
         model.addAttribute("toPage2", "/account");
         model.addAttribute("toPage3", "/role");
        return "employee";
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("register", new Register());
        model.addAttribute("title", "KSM - Employee Form");
        return "register";
    }
    
    @PostMapping("/register")
    public String registerProcess(@ModelAttribute Register register) {
        Boolean b = loginService.register(register);
        return "redirect:/login";
    }
   
}
