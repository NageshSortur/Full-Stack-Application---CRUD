package com.springboot.fullstack.simple_crud.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.fullstack.simple_crud.model.User;

public interface UserRepository extends JpaRepository<User,Long>{
	
}
