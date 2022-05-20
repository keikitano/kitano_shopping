package com.example.demo.Repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	//nameとpassで検索
	List<Users> findByNameAndPass(String name,String pass);
}
