package com.mahavir.boq.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mahavir.boq.models.Admin;

@Repository
public interface AdminDao extends MongoRepository<Admin,String>{
    
    public Admin findAdminByEmail(String email);
}
