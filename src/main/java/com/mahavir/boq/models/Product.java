package com.mahavir.boq.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;

@Component
@Scope(value = "prototype")
@Document(collection = "products")
public class Product {
    
    @Id
    private String modelName;

    private String itemName;

    private String description[];

    private String unitRate;

    private String hsnCode;

    private String gstRate;

    public Product() {
    }

    public Product(String modelName, String itemName, String[] description, String unitRate, String hsnCode, String gstRate) {
        this.modelName = modelName;
        this.itemName = itemName;
        this.description = description;
        this.unitRate = unitRate;
        this.hsnCode = hsnCode;
        this.gstRate = gstRate;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public String getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(String unitRate) {
        this.unitRate = unitRate;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getGstRate() {
        return gstRate;
    }

    public void setGstRate(String gstRate) {
        this.gstRate = gstRate;
    }

    @Override
    public String toString() {
        return "Product [modelName=" + modelName + ", itemName=" + itemName + ", description=" + description
                + ", unitRate=" + unitRate + ", hsnCode=" + hsnCode + ", gstRate=" + gstRate + "]";
    }

}
