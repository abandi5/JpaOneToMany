package com.springboot.onetomany.jpaonetomany.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.springboot.onetomany.jpaonetomany.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
