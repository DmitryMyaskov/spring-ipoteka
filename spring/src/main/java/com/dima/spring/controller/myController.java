package com.dima.spring.controller;

import com.dima.spring.DAO.CustomerDAO;
import com.dima.spring.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Validated
public class myController {
    @Autowired
    private CustomerDAO customerDAO;


    @GetMapping("customers")
    public List<Customer> show(){
        List<Customer> customerList = customerDAO.getCustomerAll();
        return customerList;
    }

    @GetMapping("customer/{id}")
    public Optional<Customer> getMortgage(@PathVariable(value = "id") Long Id) {
        return customerDAO.findById(Id);
    }

    @PostMapping("/customer")
    public Customer createMortgage(@Valid @RequestBody Customer customer,BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
                System.out.println(bindingResult.getModel());
        }
        customerDAO.save(customer);
        return customer;
    }


}


//
//    @PostMapping("/user")
//    public User createMortgage(@Valid @RequestBody User user) {
//        userDAO.save(user);
//        return user;
//    }
