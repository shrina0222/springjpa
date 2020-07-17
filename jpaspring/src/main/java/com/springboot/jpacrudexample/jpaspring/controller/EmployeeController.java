package com.springboot.jpacrudexample.jpaspring.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.jpacrudexample.jpaspring.exception.ResourceNotFoundException;
import com.springboot.jpacrudexample.jpaspring.model.Employee;
import com.springboot.jpacrudexample.jpaspring.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//create get all the employees api
	@GetMapping("/employees")
	public List<Employee> GetAllEmployees() {
		return employeeRepository.findAll();
	}
	
	
	//create employee
	@PostMapping("/employees")
	public Employee createEmployee(@Validated @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	
	@GetMapping("/employees/{id}")
	//get employee by id
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") long employeeid) throws ResourceNotFoundException {
		Employee employee = employeeRepository.findById(employeeid).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeid));
		        return ResponseEntity.ok().body(employee);
		
	}
	
	
	//update employee
	@PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
         @Validated @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        //final Employee updatedEmployee = employeeRepository.save(employee);
        employeeRepository.save(employee);
        return ResponseEntity.ok().body(employee);
        //return ResponseEntity.ok(updatedEmployee);
	}
	
	
	
	//delete employee by id
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws ResourceNotFoundException {
    	 employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

            employeeRepository.deleteById(employeeId);
            //Map<String, Boolean> response = new HashMap<>();
            //response.put("deleted", Boolean.TRUE);
            return ResponseEntity.ok().build();
    }
}
