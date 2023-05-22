package com.mahavir.boq.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.mahavir.boq.daos.AdminDao;
import com.mahavir.boq.helper.JwtUtil;
import com.mahavir.boq.models.Admin;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    public AdminDao adminDao;

    @Autowired
    public Admin admin;

    @Autowired
    public JwtUtil jwtUtil;

    public Admin findByAdminname(String email){
        return adminDao.findAdminByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String useremail) throws UsernameNotFoundException {
       //username means email 
        admin = findByAdminname(useremail);
        if(admin != null){

            return  new User(admin.getEmail(), admin.getpassword(), new ArrayList<>()); 
        }
        else{
            throw new UsernameNotFoundException("User not found");
        }
    }

    public ResponseEntity<?> getUserByToken(String token){
        try {
            String email = jwtUtil.extractUsername(token);
            UserDetails uDetails = loadUserByUsername(email);  //username == email id
            return ResponseEntity.ok(uDetails); 
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }


}
