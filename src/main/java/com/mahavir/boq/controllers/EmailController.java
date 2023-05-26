package com.mahavir.boq.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mahavir.boq.helper.Mail;
import com.mahavir.boq.helper.ResponseMessage;
import com.mahavir.boq.services.EmailService;

@RestController
public class EmailController {
    @Autowired
    public EmailService emailVerificationService;

    @Autowired
    public ResponseMessage responseMessage;

    @GetMapping("/verify-email/{email}")
    public ResponseEntity<?> getOTP(@PathVariable("email")String email){
        try {
            System.out.println("Processing request to get OTP for email: " + email);
            // return emailVerificationService.getOtp(email);
            Random random = new Random();
            int number = random.nextInt(999999);

            String otp = String.format("%06d", number);
            
            Mail mail = new Mail();

            // mail.setFrom("shivam380.testing@gmail.com");
            mail.setFrom("edata@mahavirelectronics.net");
            mail.setTo(email);
            mail.setSubject("Mahavir Electronics-OTP Verification");

            

            Map model = new HashMap();
            model.put("name", "Customer,");
            model.put("location", "Belgium");
            model.put("signature", "https://memorynotfound.com");
            model.put("otp", otp);
            mail.setModel(model);
            System.out.println("Calling sendSimpleMessage function");
            emailVerificationService.sendSimpleMessage(mail);
            System.out.println("Called sendSimpleMessage function");
            responseMessage.setMessage(otp);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/resend-otp/{email}")
    public ResponseEntity<?> resendOTP(@PathVariable("email") String email){
        try {
            Random random = new Random();
            int number = random.nextInt(999999);

            String otp = String.format("%06d", number);
            
            Mail mail = new Mail();
            mail.setFrom("edata@mahavirelectronics.net");
            mail.setTo(email);
            mail.setSubject("Mahavir Electronics-New OTP");


            Map model = new HashMap();
            model.put("name", "Customer,");
            model.put("location", "Belgium");
            model.put("signature", "https://memorynotfound.com");
            model.put("otp", otp);
            mail.setModel(model);

            emailVerificationService.sendSimpleMessage(mail);
            responseMessage.setMessage(otp);
            return ResponseEntity.status(HttpStatus.OK).body(responseMessage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/forgotPassword/{email}")
    public ResponseEntity<?> forgotPassword(@PathVariable("email")String email){
        try {
            Random random = new Random();
            int number = random.nextInt(999999);

            String otp = String.format("%06d", number);
            
            Mail mail = new Mail();
            mail.setFrom("edata@mahavirelectronics.net");
            mail.setTo(email);
            mail.setSubject("Forgot Password-Mahavir Electronics");

            Map model = new HashMap();
            model.put("name", "Customer,");
            model.put("location", "Belgium");
            model.put("signature", "https://memorynotfound.com");
            model.put("otp", otp);
            mail.setModel(model);
            return emailVerificationService.forgotPassword(mail);

        }catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
