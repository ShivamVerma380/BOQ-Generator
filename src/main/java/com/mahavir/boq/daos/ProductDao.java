package com.mahavir.boq.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mahavir.boq.models.Product;

@Repository
public interface ProductDao extends MongoRepository<Product, String>{
    
    public Product findByModelName(String modelName);

}
