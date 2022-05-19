package com.example.demo.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Categories;

@Repository
public interface CategoryRepository extends JpaRepository<Categories,Integer>{

}
