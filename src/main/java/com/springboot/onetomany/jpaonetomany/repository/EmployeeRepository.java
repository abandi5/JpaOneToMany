/**
 * 
 */
package com.springboot.onetomany.jpaonetomany.repository;

import com.springboot.onetomany.jpaonetomany.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * @author 
 *
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
