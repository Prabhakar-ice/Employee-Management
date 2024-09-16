package com.prabha.Employee.Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prabha.Employee.Management.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users,Integer>{

	Users findByUsername(String username);

}
