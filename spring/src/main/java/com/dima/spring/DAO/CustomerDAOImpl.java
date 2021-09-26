package com.dima.spring.DAO;


import com.dima.spring.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class CustomerDAOImpl implements CustomerDAO<Customer>{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional(readOnly = true)
    public List<Customer> getCustomerAll() {
        Session session= sessionFactory.getCurrentSession();
        Query<Customer> query = session.createQuery("from Customer ",Customer.class);
        List<Customer> customerList=query.getResultList();

        return customerList;
    }

    @Transactional
    public Optional<Customer> findById(Long id){
        Session session = sessionFactory.getCurrentSession();
        Optional<Customer> customer = Optional.ofNullable(session.get(Customer.class,id));
        return customer;
    }

    @Override
    @Transactional
    public Serializable save(Customer customer) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(customer);
    }

}
