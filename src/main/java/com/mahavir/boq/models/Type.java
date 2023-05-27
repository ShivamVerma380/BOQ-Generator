package com.mahavir.boq.models;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class Type {
    
    private String typename;

    private ArrayList<Brand> brands;

    public Type() {
        this.brands = new ArrayList<Brand>();
    }

    public Type(String typename, ArrayList<Brand> brands) {
        this.typename = typename;
        this.brands = brands;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public ArrayList<Brand> getBrands() {
        return brands;
    }

    public void setBrands(ArrayList<Brand> brands) {
        this.brands = brands;
    }

    

}
