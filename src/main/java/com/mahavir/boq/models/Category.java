package com.mahavir.boq.models;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "categories")
public class Category {
    
    @Id
    private String categoryname;


    private ArrayList<Type> types;

    public Category() {
        this.types = new ArrayList<Type>();
    }

    public Category(String categoryname, ArrayList<Type> types) {
        this.categoryname = categoryname;
        this.types = types;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public ArrayList<Type> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<Type> types) {
        this.types = types;
    }

    
}
