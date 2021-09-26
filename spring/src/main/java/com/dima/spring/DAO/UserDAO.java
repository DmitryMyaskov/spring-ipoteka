package com.dima.spring.DAO;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO<User>{
    public List<User> getUserAll();

    public Optional<User> findById(Long id);

    public Serializable save(User entity);
}
