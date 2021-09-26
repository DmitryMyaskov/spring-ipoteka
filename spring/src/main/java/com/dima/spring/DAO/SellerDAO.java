package com.dima.spring.DAO;

import com.dima.spring.entity.Seller;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SellerDAO {
    public List<Seller>getSellerAll();
}
