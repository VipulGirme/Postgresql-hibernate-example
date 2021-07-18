package com.postgresql.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.postgresql.demo.exception.ResourceNotFoundException;
import com.postgresql.demo.model.Employee;
import com.postgresql.demo.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	EmployeeRepository repo;

	// getEmployees
	@GetMapping("employees")
	public List<Employee> getAll() {
		return this.repo.findAll();
	}

	// get Employee by ID
	@GetMapping("employee/{id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable(value = "id") Long employeeID)
			throws ResourceNotFoundException {
		Employee employee = repo.findById(employeeID)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeID));
		return ResponseEntity.ok().body(employee);
	}

	// save employee
	@PostMapping("save")
	public ResponseEntity<?> saveEmployee(@RequestBody Employee employee) {
		Employee emp = repo.findByemail(employee.getEmail());
		if (emp == null) {
			emp = repo.save(employee);
			return ResponseEntity.ok().body(employee);
		} else {
			return ResponseEntity.badRequest().body("Emp already present");
		}

	}

	// update

	// delete

}
