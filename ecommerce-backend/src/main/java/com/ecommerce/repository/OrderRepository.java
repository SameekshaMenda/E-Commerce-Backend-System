package com.ecommerce.repository;


import com.ecommerce.entity.*; 
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderRepository extends JpaRepository<Order, Long> {
  java.util.List<Order> findByUser(User user);
}
