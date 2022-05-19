package com.example.demo.Repositry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Items;

@Repository
public interface ItemRepository extends JpaRepository<Items,Integer>{
	//List<Items> findBycategoryKeyByCode();
}
