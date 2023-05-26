package com.mahavir.boq.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.mahavir.boq.config.MySecurityConfig;
import com.mahavir.boq.daos.AdminDao;
import com.mahavir.boq.helper.JwtUtil;
import com.mahavir.boq.helper.ResponseMessage;
import com.mahavir.boq.models.Admin;

@Component
public class AdminService {
    
    @Autowired
    public Admin admin;

    @Autowired
    public AdminDao adminDao;

    @Autowired
    public ResponseMessage responseMessage;

    @Autowired
    public MySecurityConfig mySecurityConfig;

    @Autowired
    public JwtUtil jwtUtil;

    @Autowired
    public CustomUserDetailsService customUserDetailsService;

    @Autowired
    public AuthenticationManager authenticationManager;

    public ResponseEntity<?> register(String email,String password){
        try {
            admin = adminDao.findAdminByEmail(email);

            if(admin!=null){
                responseMessage.setMessage("Admin already exists");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMessage);
            }
            
            String encodedPassword = mySecurityConfig.passwordEncoder().encode(password);

            admin = new Admin(email, encodedPassword);

            adminDao.save(admin);

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            String token = jwtUtil.generateToken(userDetails);

            responseMessage.setMessage(token);

            return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);



        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    public ResponseEntity<?> login(String email, String password) {
        try {
            Admin admin = adminDao.findAdminByEmail(email);

            if(admin==null){
                responseMessage.setMessage("No admin account exists with this email");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMessage);
            }

            //spring security
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

            if(!bCryptPasswordEncoder.matches(password, admin.getpassword())){
                responseMessage.setMessage("Password is incorrect");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(responseMessage);
            }

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);
            String token = jwtUtil.generateToken(userDetails);

            responseMessage.setMessage(token);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
            
            
        } catch (Exception e) {

            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }


}
