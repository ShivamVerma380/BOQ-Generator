package com.mahavir.boq.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class Brand {
    

    private String brandName;

    private Map<String, ArrayList<String>> models;
    

    public Brand() {
        this.models = new HashMap<>();
    }

    public Brand(String brandName, Map<String, ArrayList<String>> models) {
        this.brandName = brandName;
        this.models = models;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Map<String, ArrayList<String>> getModels() {
        return models;
    }

    public void setModels(Map<String, ArrayList<String>> models) {
        this.models = models;
    }

    

}
