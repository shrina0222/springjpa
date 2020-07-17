package com.springboot.jpacrudexample.jpaspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.jpacrudexample.jpaspring.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
