package com.mahavir.boq.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mahavir.boq.models.Category;

@Repository
public interface CategoryDao extends MongoRepository<Category, String>{
    
    public Category findByCategoryname(String categoryname);

}
