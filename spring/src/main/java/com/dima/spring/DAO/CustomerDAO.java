package com.dima.spring.DAO;

import com.dima.spring.entity.Customer;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerDAO<Customer> {
    public List<Customer> getCustomerAll();
    public Optional<Customer> findById(Long id);
    public Serializable save(Customer customer);
}
