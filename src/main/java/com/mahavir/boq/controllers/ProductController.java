package com.mahavir.boq.controllers;

import javax.mail.Multipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mahavir.boq.services.ProductService;

@RestController
public class ProductController {
    
    @Autowired
    public ProductService productService;

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestParam("file") MultipartFile file){
        try {
            return productService.addProduct(file.getInputStream());
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    

}
