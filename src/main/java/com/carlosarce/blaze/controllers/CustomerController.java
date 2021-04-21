package com.carlosarce.blaze.controllers;

import com.carlosarce.blaze.dto.CustomerDTO;
import com.carlosarce.blaze.models.Customer;
import com.carlosarce.blaze.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping("/customers")
    public ResponseEntity<CustomerDTO> getCustomers() {
        CustomerDTO customerDTO = new CustomerDTO();
        try {
            List<Customer> customers = customerRepository.findAll();
            if(customers.size() > 0) {
                customerDTO.setCode("000");
                customerDTO.setMessage("success");
                customerDTO.setData(customers);
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            } else {
                customerDTO.setCode("001");
                customerDTO.setMessage("not data found");
                return new ResponseEntity<>(customerDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            customerDTO.setCode("500");
            customerDTO.setMessage(ex.getMessage());
            customerDTO.setData(ex);
            return new ResponseEntity<>(customerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody Customer req) {
        CustomerDTO customerDTO = new CustomerDTO();
        try {
            Customer customer = customerRepository.save(req);
            customerDTO.setCode("000");
            customerDTO.setMessage("success");
            customerDTO.setData(customer);
            return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
        } catch (Exception ex) {
            customerDTO.setCode("500");
            customerDTO.setMessage(ex.getMessage());
            customerDTO.setData(ex);
            return new ResponseEntity<>(customerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/customers")
    public ResponseEntity<CustomerDTO> updateCustomer(@RequestBody Customer req) {
        Optional<Customer> customerOptional = customerRepository.findById(req.id);
        CustomerDTO customerDTO = new CustomerDTO();
        try {
            if(customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                customer.setFirstName(req.getFirstName());
                customer.setLastName(req.getLastName());
                customer.setEmail(req.getEmail());
                customer.setPhoneNumber(req.getPhoneNumber());
                customer.setBirthDate(req.getBirthDate());

                customerDTO.setCode("000");
                customerDTO.setMessage("success");
                customerDTO.setData(customerRepository.save(customer));
                return new ResponseEntity<>(customerDTO, HttpStatus.OK);
            } else {
                customerDTO.setCode("001");
                customerDTO.setMessage("not data found");
                return new ResponseEntity<>(customerDTO, HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            customerDTO.setCode("500");
            customerDTO.setMessage(ex.getMessage());
            customerDTO.setData(ex);
            return new ResponseEntity<>(customerDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
