package com.carlosarce.blaze.repositories;

import com.carlosarce.blaze.models.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
