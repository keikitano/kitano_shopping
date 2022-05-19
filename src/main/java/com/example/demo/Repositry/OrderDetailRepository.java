package com.example.demo.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.OrderDetail;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

}
