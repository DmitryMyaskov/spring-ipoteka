package com.dima.spring.DAO;
import com.dima.spring.entity.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO<User>{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<User> getUserAll() {
        Session session= sessionFactory.getCurrentSession();
        List<User> query = session.createQuery("from User ",User.class).getResultList();
        return query;
    }
    @Override
    @Transactional
    public Optional<User> findById(Long id){
        Session session= sessionFactory.getCurrentSession();
        Optional<User> user = Optional.ofNullable(session.get(User.class,id));
        return user;
    }



    @Override
    @Transactional
    public Serializable save(User entity) {
        Session session= sessionFactory.getCurrentSession();
        return session.save(entity);
    }
}
