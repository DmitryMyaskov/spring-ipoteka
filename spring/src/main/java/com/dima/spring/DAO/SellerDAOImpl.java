package com.dima.spring.DAO;

import com.dima.spring.entity.Seller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class SellerDAOImpl implements SellerDAO{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Seller> getSellerAll() {
        Session session= sessionFactory.getCurrentSession();
        List<Seller> query = session.createQuery("from Seller ",Seller.class).getResultList();
        return query;
    }
}
