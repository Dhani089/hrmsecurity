package com.hrm.hrmsecurity.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hrm.hrmsecurity.model.Employee;

@Repository
public interface JwtUserDetailsRepository extends JpaRepository<Employee, Long>{

	Employee findByUsername(String username);
}
