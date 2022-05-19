package com.example.demo.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Ordered;

@Repository
public interface OrderRepository extends JpaRepository<Ordered,Integer>{

}
