/**
 * 
 */
package com.springboot.onetomany.jpaonetomany.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.springboot.onetomany.jpaonetomany.entity.Department;
import com.springboot.onetomany.jpaonetomany.entity.Employee;
import com.springboot.onetomany.jpaonetomany.service.EmployeeService;

/**
 * @author 
 *
 */
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired 
	private RestTemplate restTemplate;
	
	
	
	

	@GetMapping("/get-employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> employees = employeeService.getAllEmployees();
		ResponseEntity<String> forEntity = restTemplate.getForEntity("http://localhost:8081/getDeptDetails", String.class);
		String body = forEntity.getBody();
		System.out.println("bosy ::: "+body);
		return new ResponseEntity<>(employees, HttpStatus.OK);
	}
	
	@GetMapping("/get-external-departments")
	public ResponseEntity<List<Department>> getAllDepartmentsFromExternalService() {
	
		ResponseEntity<List<Department>> deptList = restTemplate.exchange("http://localhost:8081/getDeptDetails", HttpMethod.GET, null, new ParameterizedTypeReference<List<Department>>() {
        });
		//String body = forEntity.getBody();
		//System.out.println("bosy ::: "+body);
		return new ResponseEntity<>(deptList.getBody(), HttpStatus.OK);
	}

	@PostMapping("/saveEmployee")
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.addEmployee(employee);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@PutMapping("/updateEmployee")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
		Employee emp = employeeService.editEmployees(employee);
		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@DeleteMapping("/deleteEmployee")
	public ResponseEntity<String> deleteEmployee(@RequestParam(name = "employeeId") Integer employeeId) {
		employeeService.deleteEmployees(employeeId);
		return new ResponseEntity<>("Employee with ID :" + employeeId + " deleted successfully", HttpStatus.OK);
	}

}
