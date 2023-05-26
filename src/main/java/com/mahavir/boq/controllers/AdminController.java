package com.mahavir.boq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mahavir.boq.helper.ResponseMessage;
import com.mahavir.boq.services.AdminService;

@RestController
public class AdminController {
    
    @Autowired
    public ResponseMessage responseMessage;


    @Autowired
    public AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestParam("email") String email,@RequestParam("password") String password){
        try {
            return adminService.register(email, password);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam("email") String email,@RequestParam("password") String password){
        try {
            return adminService.login(email, password);
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

}
