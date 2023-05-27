package com.mahavir.boq.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mahavir.boq.helper.ResponseMessage;
import com.mahavir.boq.services.CategoryService;

@RestController
public class CategoryController {
    
    @Autowired
    public CategoryService categoryService;

    @Autowired
    public ResponseMessage responseMessage;

    @PostMapping("/categories")
    public ResponseEntity<?> addCategories(@RequestParam("file") MultipartFile file){
        try {
            return categoryService.addCategories(file.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            responseMessage.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    
}   
