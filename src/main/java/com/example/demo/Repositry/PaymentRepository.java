package com.example.demo.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Payments;

@Repository
public interface PaymentRepository extends JpaRepository<Payments,Integer>{

}
