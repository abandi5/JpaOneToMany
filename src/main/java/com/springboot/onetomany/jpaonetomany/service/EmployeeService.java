package com.springboot.onetomany.jpaonetomany.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.onetomany.jpaonetomany.entity.Department;
import com.springboot.onetomany.jpaonetomany.entity.Employee;
import com.springboot.onetomany.jpaonetomany.repository.DepartmentRepository;
import com.springboot.onetomany.jpaonetomany.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	public Employee addEmployee(Employee employee) {
		Department dept = departmentRepository.findById(employee.getDepartment().getDeptId()).orElse(null);
		System.out.println(dept);
		if (null == dept) {
			dept = new Department();
		}
		dept.setDeptName(employee.getDepartment().getDeptName());
		employee.setDepartment(dept);
		return employeeRepository.save(employee);
	}

	public Employee editEmployees(Employee employees) {
	Employee employee = employeeRepository.findById(employees.getEmpId()).orElse(null);
	if(null != employee)
	{
		employee.setEmpFirstName(employee.getEmpFirstName());
		employee.setEmpLastName(employee.getEmpLastName());
		employee.setEmpId(employee.getEmpId());
		Employee object= employeeRepository.save(employee);
		return object;
	}
	else
	{
		return null;
	}
	}

	public void deleteEmployees(Integer id) {
		employeeRepository.deleteById(id);
	}
}
