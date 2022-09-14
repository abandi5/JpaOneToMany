package com.springboot.onetomany.jpaonetomany.resttemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.springboot.onetomany.jpaonetomany.controller.EmployeeController;
import com.springboot.onetomany.jpaonetomany.entity.Department;

@SpringBootTest
public class RestTemplateDemo {

	@Mock
	private RestTemplate restTemplate;

	@InjectMocks
	private EmployeeController employeeController;

	@Test
	public void getAllDepartmentsFromExternalServiceTest() {

		List<Department> dept = getDepartmentList();

		Mockito.when(restTemplate.exchange(Mockito.anyString(), ArgumentMatchers.eq(HttpMethod.GET),
				ArgumentMatchers.eq(null), ArgumentMatchers.eq(new ParameterizedTypeReference<List<Department>>() {
				}))).thenReturn(new ResponseEntity(dept, HttpStatus.OK));

		ResponseEntity<List<Department>> deptList = employeeController.getAllDepartmentsFromExternalService();
		List<Department> body = deptList.getBody();
		assertNotNull(body);
		assertEquals(2, body.size());

		System.out.println(deptList.getBody());

	}

	private List<Department> getDepartmentList() {
		Department department = new Department();
		department.setDeptId(1);
		department.setDeptName("HR");

		Department department2 = new Department();
		department2.setDeptId(2);
		department2.setDeptName("IT");

		return Arrays.asList(department, department2);
	}
}
